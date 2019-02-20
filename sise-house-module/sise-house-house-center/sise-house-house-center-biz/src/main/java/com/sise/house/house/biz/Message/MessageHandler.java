package com.sise.house.house.biz.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.constants.HouseIndexKey;
import com.sise.house.house.api.constants.HouseIndexMessage;
import com.sise.house.house.api.constants.HouseIndexTemplate;
import com.sise.house.house.api.constants.HouseSuggest;
import com.sise.house.house.api.dto.response.BaiduMapLocationRespDto;
import com.sise.house.house.api.dto.response.SupportAddressRespDto;
import com.sise.house.house.biz.pojo.HouseDetail;
import com.sise.house.house.biz.pojo.HouseMsg;
import com.sise.house.house.biz.pojo.HouseTag;
import com.sise.house.house.biz.service.HouseService;
import com.sise.house.house.biz.service.SearchService;
import com.sise.house.house.biz.service.SupportAddressService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.rest.RestStatus;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/18 21:08
 */
@Service
@Slf4j
public class MessageHandler {
    private static final String INDEX_NAME = "lianjia";

    private static final String INDEX_TYPE = "house";

    private static final String INDEX_TOPIC = "house_build";

    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private ModelMapper modelMapper;
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;
    @Resource
    private HouseService houseService;
    @Resource
    private SearchService searchService;
    @Resource
    private SupportAddressService addressService;
    @Resource
    private TransportClient esClient;

    @KafkaListener(topics = INDEX_TOPIC)
    private void handleMessage(String content) {
        try {
            HouseIndexMessage message = objectMapper.readValue(content, HouseIndexMessage.class);

            switch (message.getOperation()) {
                case HouseIndexMessage.INDEX:
                    this.createOrUpdateIndex(message);
                    break;
                case HouseIndexMessage.REMOVE:
                    this.removeIndex(message);
                    break;
                default:
                    log.warn("Not support message content " + content);
                    break;
            }
        } catch (IOException e) {
            log.error("Cannot parse json for " + content, e);
        }
    }

    /**
     * 从数据库查询出数据,再从es数据，存在则update 否则 create
     *
     * @param message
     */
    private void createOrUpdateIndex(HouseIndexMessage message) {
        Long houseId = message.getHouseId();
        HouseMsg houseMsg = houseService.findhouseById(houseId);
        if (houseMsg == null) {
            log.error("Index house {} dose not exist!", houseId);
            searchService.sendMessageToIndex(houseId, message.getRetry() + 1);
            return;
        }
        HouseIndexTemplate indexTemplate = new HouseIndexTemplate();
        modelMapper.map(houseMsg, indexTemplate);

        HouseDetail detailByHouse = houseService.findDetailByHouseId(houseId);
        if (detailByHouse == null) {
            log.error("Index HouseDetail {} dose not exist!", houseId);
            searchService.sendMessageToIndex(houseId, message.getRetry() + 1);
            return;
        }
        modelMapper.map(detailByHouse, indexTemplate);
        SupportAddressRespDto city = addressService.findCityByEnNameAndLevel(houseMsg.getCityEnName());
        if (city == null) {
            log.error("Index city {} dose not exist!", houseId);
            searchService.sendMessageToIndex(houseId, message.getRetry() + 1);
            return;
        }
        SupportAddressRespDto region = addressService.findRegionByRegionNameAndLevel(houseMsg.getRegionEnName(), houseMsg.getCityEnName());
        if (region == null) {
            log.error("Index region {} dose not exist!", houseId);
            searchService.sendMessageToIndex(houseId, message.getRetry() + 1);
            return;
        }
        String address = city.getCnName() + region.getCnName() + houseMsg.getStreet() + houseMsg.getDistrict() + detailByHouse.getAddress();
        BaiduMapLocationRespDto baiduMapLocation = addressService.getBaiduMapLocation(city.getCnName(), address);
        if (baiduMapLocation == null) {
            searchService.sendMessageToIndex(message.getHouseId(), message.getRetry() + 1);
            return;
        }
        indexTemplate.setLocation(baiduMapLocation);
        List<HouseTag> tags = houseService.findTagsByHouseId(houseId);
        if (tags != null && !tags.isEmpty()) {
            List<String> tagStrings = new ArrayList<>();
            tags.forEach(houseTag -> tagStrings.add(houseTag.getName()));
            indexTemplate.setTags(tagStrings);
        }
        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(INDEX_NAME).setTypes(INDEX_TYPE)
                .setQuery(QueryBuilders.termQuery(HouseIndexKey.HOUSE_ID, houseId));
        log.debug(requestBuilder.toString());
        SearchResponse searchResponse = requestBuilder.get();
        boolean success;
        long totalHit = searchResponse.getHits().getTotalHits();
        if (totalHit == 0) {
            success = create(indexTemplate);
        } else if (totalHit == 1) {
            String esId = searchResponse.getHits().getAt(0).getId();
            success = update(esId, indexTemplate);
        } else {
            success = deleteAndCreate(totalHit, indexTemplate);
        }
//        ServerResponse serverResponse = addressService.lbsUpload(baiduMapLocation, houseMsg.getStreet() + houseMsg.getDistrict(),
//                city.getCnName() + region.getCnName() + houseMsg.getStreet() + houseMsg.getDistrict(),
//                message.getHouseId(), houseMsg.getPrice(), houseMsg.getArea());
//
//        if (!success || !serverResponse.isSuccess()) {
//            searchService.sendMessageToIndex(message.getHouseId(), message.getRetry() + 1);
//        } else {
//            log.debug("Index success with house " + houseId);
//        }
    }

    /**
     * 去除索引
     *
     * @param message
     */
    private void removeIndex(HouseIndexMessage message) {
        Long houseId = message.getHouseId();
        DeleteByQueryRequestBuilder builder = DeleteByQueryAction.INSTANCE
                .newRequestBuilder(esClient)
                .filter(QueryBuilders.termQuery(HouseIndexKey.HOUSE_ID, houseId))
                .source(INDEX_NAME);

        log.debug("Delete by query for house: " + builder);

        BulkByScrollResponse response = builder.get();
        long deleted = response.getDeleted();
        log.debug("Delete total " + deleted);

        ServerResponse serverResponse = addressService.removeLbs(houseId);


        if (!serverResponse.isSuccess() || deleted <= 0) {
            log.warn("Did not remove data from es for response: " + response);
            // 重新加入消息队列
            searchService.sendMessageToRemoveIndex(houseId, message.getRetry() + 1);
        }
    }

    /**
     * 创建索引
     *
     * @param indexTemplate
     * @return
     */
    private boolean create(HouseIndexTemplate indexTemplate) {
        if (!updateSuggest(indexTemplate)) {
            return false;
        }
        try {
            IndexResponse response = this.esClient.prepareIndex(INDEX_NAME, INDEX_TYPE)
                    .setSource(objectMapper.writeValueAsBytes(indexTemplate), XContentType.JSON).get();
            log.debug("Create index with house: " + indexTemplate.getHouseId());
            if (response.status() == RestStatus.CREATED) {
                return true;
            } else {
                return false;
            }
        } catch (JsonProcessingException e) {
            log.error("Error to index house " + indexTemplate.getHouseId(), e);
            return false;
        }
    }

    /**
     * 更新索引
     *
     * @param esId
     * @param indexTemplate
     * @return
     */
    private boolean update(String esId, HouseIndexTemplate indexTemplate) {
        if (!updateSuggest(indexTemplate)) {
            return false;
        }
        try {
            UpdateResponse response = this.esClient.prepareUpdate(INDEX_NAME, INDEX_TYPE, esId).setDoc(objectMapper.writeValueAsBytes(indexTemplate), XContentType.JSON).get();
            log.debug("Update index with house: " + indexTemplate.getHouseId());
            if (response.status() == RestStatus.OK) {
                return true;
            } else {
                return false;
            }
        } catch (JsonProcessingException e) {
            log.error("Error to index house " + indexTemplate.getHouseId(), e);
            return false;
        }
    }

    /**
     * 删除重建索引
     *
     * @param totalHit
     * @param indexTemplate
     * @return
     */
    private boolean deleteAndCreate(long totalHit, HouseIndexTemplate indexTemplate) {
        DeleteByQueryRequestBuilder builder = DeleteByQueryAction.INSTANCE
                .newRequestBuilder(esClient)
                .filter(QueryBuilders.termQuery(HouseIndexKey.HOUSE_ID, indexTemplate.getHouseId()))
                .source(INDEX_NAME);
        log.debug("Delete by query for house: " + builder);
        BulkByScrollResponse response = builder.get();
        long deleted = response.getDeleted();
        if (deleted != totalHit) {
            log.warn("Need delete {}, but {} was deleted!", totalHit, deleted);
            return false;
        } else {
            return create(indexTemplate);
        }
    }

    /**
     * 更新自动补全索引
     *
     * @param indexTemplate
     * @return
     */
    private boolean updateSuggest(HouseIndexTemplate indexTemplate) {
        AnalyzeRequestBuilder requestBuilder = new AnalyzeRequestBuilder(
                this.esClient, AnalyzeAction.INSTANCE, INDEX_NAME, indexTemplate.getName(),
                indexTemplate.getLayoutDesc(), indexTemplate.getRoundService(),
                indexTemplate.getDescription(), indexTemplate.getSubwayLineName(),
                indexTemplate.getSubwayStationName());
        requestBuilder.setAnalyzer("ik_smart");
        AnalyzeResponse response = requestBuilder.get();
        List<AnalyzeResponse.AnalyzeToken> tokens = response.getTokens();
        if (tokens == null) {
            log.warn("Can not analyze token for house: " + indexTemplate.getHouseId());
            return false;
        }
        List<HouseSuggest> suggests = new ArrayList<>();
        for (AnalyzeResponse.AnalyzeToken token : tokens) {
            if ("<NUM>".equals(token.getType()) || token.getTerm().length() < 2) {
                continue;
            }
            HouseSuggest suggest = new HouseSuggest();
            suggest.setInput(token.getTerm());
            suggests.add(suggest);
        }
        HouseSuggest suggest = new HouseSuggest();
        suggest.setInput(indexTemplate.getDistrict());
        suggests.add(suggest);
        indexTemplate.setSuggest(suggests);
        return true;
    }


}
