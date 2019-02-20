package com.sise.house.house.biz.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.common.exception.MyException;
import com.sise.common.exception.ResultEnum;
import com.sise.common.rest.ServiceMultiResult;
import com.sise.common.util.IdWorker;
import com.sise.house.house.api.constants.HouseSort;
import com.sise.house.house.api.constants.HouseStatus;
import com.sise.house.house.api.constants.PhotoForm;
import com.sise.house.house.api.constants.RentSearch;
import com.sise.house.house.api.dto.request.HouseMsgReqDto;
import com.sise.house.house.api.dto.request.MapSearchRepDto;
import com.sise.house.house.api.dto.response.HouseMsgRespDto;
import com.sise.house.house.api.dto.response.HousePictureRespDto;
import com.sise.house.house.api.dto.response.SubwayRespDto;
import com.sise.house.house.api.dto.response.SubwayStationRespDto;
import com.sise.house.house.biz.dao.*;
import com.sise.house.house.biz.pojo.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 房产信息服务层
 * @Auther: 冲之
 * @Date: 2019/2/18 17:08
 */
@Service
@Transactional
public class HouseService {
    @Resource
    private ModelMapper modelMapper;
    @Resource
    private IdWorker idWorker;
    @Resource
    private HouseMsgMapper msgMapper;
    @Resource
    private HouseDetailMapper detailMapper;
    @Resource
    private HouseCommunityMapper communityMapper;
    @Resource
    private HousePictureMapper pictureMapper;
    @Resource
    private HouseTagMapper tagMapper;
    @Resource
    private HouseSubscribeMapper subscribeMapper;
    @Resource
    private SupportAddressService addressService;
    @Value("${qiniu.cdn.prefix}")
    private String cdnPrefix;
    @Resource
    private SearchService searchService;

    /**
     * 新增房源
     *
     * @param houseMsgReqDto
     * @return
     */
    public Long addHouse(HouseMsgReqDto houseMsgReqDto) {
        HouseDetail detail = new HouseDetail();
        wrapperDetailInfo(detail, houseMsgReqDto);
        HouseMsg houseMsg = modelMapper.map(houseMsgReqDto, HouseMsg.class);
        Date now = new Date();
        houseMsg.setCreateTime(now);
        houseMsg.setLastUpdateTime(now);
        houseMsg.setId(idWorker.nextId());
        houseMsg.setStatus(0);
        msgMapper.insert(houseMsg);
        detail.setId(idWorker.nextId());
        detail.setHouseId(houseMsg.getId());
        detailMapper.insert(detail);
        generatePictures(houseMsgReqDto, houseMsg.getId());

        List<String> tags = houseMsgReqDto.getTags();
        if (tags != null && !tags.isEmpty()) {
            tags.stream().forEach(e -> {
                HouseTag houseTag = new HouseTag();
                houseTag.setId(idWorker.nextId());
                houseTag.setHouseId(houseMsg.getId());
                houseTag.setName(e);
                tagMapper.insert(houseTag);
            });
        }
        return houseMsg.getId();
    }

    /**
     * 修改房屋信息
     *
     * @param houseMsgReqDto
     */
    public void updateHouse(HouseMsgReqDto houseMsgReqDto) {
        HouseMsg houseMsg = msgMapper.selectByPrimaryKey(houseMsgReqDto.getId());
        if (houseMsg == null) {
            throw new MyException(ResultEnum.UN_EXIST_HOUSE);
        }
        modelMapper.map(houseMsgReqDto, houseMsg);
        houseMsg.setLastUpdateTime(new Date());
        msgMapper.updateByPrimaryKeySelective(houseMsg);
        HouseDetail detail = findDetailByHouseId(houseMsg.getId());
        modelMapper.map(houseMsgReqDto, detail);
        deletePicture(houseMsgReqDto.getId());
        generatePictures(houseMsgReqDto, houseMsg.getId());
        if (houseMsgReqDto.getStatus() == HouseStatus.PASSES.getValue()) {
            searchService.sendMessageToIndex(houseMsg.getId(), 0);
        }

    }

    /**
     * 审核房屋状态
     *
     * @param houseId
     * @param status
     */
    public void auditHouseStatus(Long houseId, Integer status) {
        HouseMsg houseMsg = msgMapper.selectByPrimaryKey(houseId);
        if (houseMsg == null) {
            throw new MyException(ResultEnum.UN_EXIST_HOUSE);
        }

        if (houseMsg.getStatus().equals(status)) {
            throw new MyException(1, "状态没有发生变化");
        }
        if (houseMsg.getStatus() == HouseStatus.RENTED.getValue()) {
            throw new MyException(2, "已出租的房源不允许修改状态");
        }
        if (houseMsg.getStatus() == HouseStatus.DELETED.getValue()) {
            throw new MyException(2, "已删除的资源不允许操作");
        }
        houseMsg.setStatus(status);
        msgMapper.updateByPrimaryKeySelective(houseMsg);
        if (status == HouseStatus.PASSES.getValue()) {
            searchService.sendMessageToIndex(houseId, 0);
        } else {
            searchService.sendMessageToRemoveIndex(houseId, 0);
        }
    }

    /**
     * 根据id查询 houseMsg
     *
     * @param houseId
     * @return
     */
    public HouseMsg findhouseById(Long houseId) {
        return msgMapper.selectByPrimaryKey(houseId);
    }

    /**
     * 查询房屋的全部信信息
     *
     * @param houseId
     * @return
     */
    public HouseMsgRespDto findHouseInfo(Long houseId) {
        HouseMsgRespDto result = new HouseMsgRespDto();
        HouseMsg houseMsg = msgMapper.selectByPrimaryKey(houseId);
        if (houseMsg == null) {
            throw new MyException(ResultEnum.UN_EXIST_HOUSE);
        }
        modelMapper.map(houseMsg, result);
        HouseDetail detailByHouseId = findDetailByHouseId(houseId);
        if (detailByHouseId == null) {
            throw new MyException(ResultEnum.UN_EXIST_HOUSE);
        }
        modelMapper.map(detailByHouseId, result);
        List<String> tags = new ArrayList<>();
        List<HouseTag> tagsByHouseId = findTagsByHouseId(houseId);
        if (!tagsByHouseId.isEmpty()) {
            tagsByHouseId.stream().forEach(e -> {
                tags.add(e.getName());
            });
        }
        result.setTags(tags);
        List<HousePicture> pictureByHouseId = findPictureByHouseId(houseId);
        List<PhotoForm> photoList = new ArrayList<>();
        if (!pictureByHouseId.isEmpty()) {
            pictureByHouseId.stream().forEach(e -> {
                PhotoForm photoForm = new PhotoForm();
                photoForm.setHeight(e.getHeight());
                photoForm.setWidth(e.getWidth());
                photoForm.setPath(cdnPrefix + e.getPath());
                photoList.add(photoForm);
            });
        }
        result.setPhotos(photoList);
        return result;
    }

    /**
     * 分页条件查询房屋信息列表
     *
     * @param rentSearch
     * @return
     */
    public PageInfo<HouseMsgRespDto> queryHouseInfoByPage(RentSearch rentSearch) {
        if (rentSearch.getKeywords() != null && !rentSearch.getKeywords().isEmpty()) {
            ServiceMultiResult<Long> longs = searchService.query(rentSearch);
            if (longs.getResult().isEmpty()) {
                return null;
            }
            List<HouseMsgRespDto> arrayList = new ArrayList();
            longs.getResult().stream().forEach(e -> {
                HouseMsgRespDto houseInfo = findHouseInfo(e);
                if (houseInfo != null) {
                    arrayList.add(houseInfo);
                }
            });
            PageInfo<HouseMsgRespDto> result = new PageInfo<>(arrayList);
            result.setTotal(longs.getResultSize());
            result.setPageSize(rentSearch.getSize());
            result.setPageNum(rentSearch.getStart() / rentSearch.getSize());
            return result;
        }
        return simpleQuery(rentSearch);

    }

    /**
     * 全地图查询
     *
     * @param mapSearch
     * @return
     */
    public PageInfo<HouseMsgRespDto> wholeMapQuery(MapSearchRepDto mapSearch) {
        ServiceMultiResult<Long> longServiceMultiResult = searchService.mapQuery(mapSearch.getCityEnName(), mapSearch.getOrderBy(), mapSearch.getOrderDirection(), mapSearch.getStart(), mapSearch.getSize());
        return getHouseMsgRespDtoPageInfo(mapSearch, longServiceMultiResult);
    }

    /**
     * 精确范围数据查询
     *
     * @param mapSearch
     * @return
     */
    public PageInfo<HouseMsgRespDto> boundMapQuery(MapSearchRepDto mapSearch) {
        ServiceMultiResult<Long> longServiceMultiResult = searchService.mapQuery(mapSearch);
        return getHouseMsgRespDtoPageInfo(mapSearch, longServiceMultiResult);
    }


    private PageInfo<HouseMsgRespDto> getHouseMsgRespDtoPageInfo(MapSearchRepDto mapSearch, ServiceMultiResult<Long> longServiceMultiResult) {
        if (longServiceMultiResult.getTotal() == 0) {
            return null;
        }
        List<HouseMsgRespDto> arrayList = new ArrayList();
        longServiceMultiResult.getResult().stream().forEach(e -> {
            HouseMsgRespDto houseInfo = findHouseInfo(e);
            arrayList.add(houseInfo);
        });
        PageInfo<HouseMsgRespDto> result = new PageInfo<>(arrayList);
        result.setTotal(longServiceMultiResult.getResultSize());
        result.setPageSize(mapSearch.getSize());
        result.setPageNum(mapSearch.getStart() / mapSearch.getSize());
        return result;
    }

    /**
     * 简单条件查询
     *
     * @param rentSearch
     * @return
     */
    private PageInfo<HouseMsgRespDto> simpleQuery(RentSearch rentSearch) {
        PageHelper.startPage(rentSearch.getStart() / rentSearch.getSize(), rentSearch.getSize());
        HouseMsgExample houseMsgExample = new HouseMsgExample();
        HouseMsgExample.Criteria criteria = houseMsgExample.createCriteria();
        criteria.andStatusEqualTo(HouseStatus.PASSES.getValue());
        criteria.andCityEnNameEqualTo(rentSearch.getCityEnName());
        if (HouseSort.DISTANCE_TO_SUBWAY_KEY.equals(rentSearch.getOrderBy())) {
            criteria.andDistanceToSubwayGreaterThan(-1);
            houseMsgExample.setOrderByClause("`distance_to_subway` asc");
        } else {
            houseMsgExample.setOrderByClause("`create_time` desc");

        }
        List<HouseMsg> houseMsgs = msgMapper.selectByExample(houseMsgExample);
        if (houseMsgs.isEmpty()) {
            return null;
        }
        PageInfo result = new PageInfo<>(houseMsgs);
        List<HouseMsgRespDto> arrayList = new ArrayList();
        houseMsgs.stream().forEach(e -> {
            HouseMsgRespDto houseInfo = findHouseInfo(e.getId());
            if (houseInfo != null) {
                arrayList.add(houseInfo);
            }
        });
        result.setList(arrayList);
        return result;
    }

    /**
     * 根据 houseId 查询 HouseDetail
     *
     * @param houseId
     * @return
     */
    public HouseDetail findDetailByHouseId(Long houseId) {
        HouseDetail result = new HouseDetail();
        HouseDetailExample houseDetailExample = new HouseDetailExample();
        houseDetailExample.createCriteria()
                .andHouseIdEqualTo(houseId);
        List<HouseDetail> houseDetails = detailMapper.selectByExample(houseDetailExample);
        if (!houseDetails.isEmpty()) {
            result = houseDetails.get(0);
        }
        return result;
    }

    /**
     * 根据 houseId 查询 房屋图片
     *
     * @param houseId
     * @return
     */
    public List<HousePicture> findPictureByHouseId(Long houseId) {
        HousePictureExample housePictureExample = new HousePictureExample();
        housePictureExample.createCriteria()
                .andHouseIdEqualTo(houseId);
        return pictureMapper.selectByExample(housePictureExample);
    }

    /**
     * 更新封面
     *
     * @param houseId
     * @param cover
     */
    public void updateCover(Long houseId, String cover) {
        HouseMsg houseMsg = msgMapper.selectByPrimaryKey(houseId);
        if (houseMsg == null) {
            throw new MyException(ResultEnum.UN_EXIST_HOUSE);
        }
        houseMsg.setCover(cover);
        houseMsg.setLastUpdateTime(new Date());
        msgMapper.updateByPrimaryKeySelective(houseMsg);
    }

    /**
     * 删除图片
     *
     * @param id
     */
    public void removePhoto(Long id) {
        HousePicture housePicture = pictureMapper.selectByPrimaryKey(id);
        if (housePicture == null) {
            throw new MyException(ResultEnum.UN_EXIST_PICTURE);
        }
        pictureMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据houseId查询 tag
     *
     * @param houseId
     * @return
     */
    public List<HouseTag> findTagsByHouseId(Long houseId) {
        HouseTagExample houseTagExample = new HouseTagExample();
        houseTagExample.createCriteria()
                .andHouseIdEqualTo(houseId);
        return tagMapper.selectByExample(houseTagExample);
    }

    /**
     * 房屋信息标签
     *
     * @param houseId
     * @param tags
     * @return
     */
    public Long addTag(Long houseId, String tags) {
        HouseMsg houseMsg = msgMapper.selectByPrimaryKey(houseId);
        if (houseMsg == null) {
            throw new MyException(ResultEnum.UN_EXIST_HOUSE);
        }
        HouseTagExample houseTagExample = new HouseTagExample();
        houseTagExample.createCriteria()
                .andHouseIdEqualTo(houseId)
                .andNameEqualTo(tags);
        List<HouseTag> houseTags = tagMapper.selectByExample(houseTagExample);
        if (houseTags.isEmpty()) {
            throw new MyException(ResultEnum.EXIST_TAG);
        }
        HouseTag houseTag = new HouseTag();
        houseTag.setId(idWorker.nextId());
        houseTag.setName(tags);
        houseTag.setHouseId(houseId);
        tagMapper.insert(houseTag);
        return houseTag.getId();
    }

    /**
     * 去除标签
     *
     * @param id
     */
    public void removeTag(Long id) {
        HouseTag houseTag = tagMapper.selectByPrimaryKey(id);
        if (houseTag == null) {
            throw new MyException(ResultEnum.UN_EXIST_TAG);
        }
        tagMapper.deleteByPrimaryKey(id);
    }

    /**
     * 房源详细信息对象填充
     *
     * @param houseDetail
     * @param houseMsgReqDto
     */
    private void wrapperDetailInfo(HouseDetail houseDetail, HouseMsgReqDto houseMsgReqDto) {
        SubwayRespDto subway = addressService.findSubway(houseMsgReqDto.getSubwayLineId());
        if (subway == null) {
            throw new MyException(2, "Not valid subway line!");
        }
        SubwayStationRespDto subwayStation = addressService.findSubwayStation(houseMsgReqDto.getSubwayStationId());
        if (subwayStation == null) {
            throw new MyException(2, "Not valid subway station!");
        }
        houseDetail.setSubwayLineId(subway.getId());
        houseDetail.setSubwayLineName(subway.getName());
        houseDetail.setSubwayStationId(subwayStation.getId());
        houseDetail.setSubwayStationName(subwayStation.getName());
        houseDetail.setDescription(houseMsgReqDto.getDescription());
        houseDetail.setAddress(houseMsgReqDto.getAddress());
        houseDetail.setLayoutDesc(houseMsgReqDto.getLayoutDesc());
        houseDetail.setRoundService(houseMsgReqDto.getRoundService());
        houseDetail.setTraffic(houseMsgReqDto.getTraffic());
    }


    /**
     * 图片对象列表信息填充
     *
     * @param houseMsgReqDto
     * @param houseId
     * @return
     */
    private void generatePictures(HouseMsgReqDto houseMsgReqDto, Long houseId) {
        houseMsgReqDto.getPhotos().stream().forEach(e -> {
            HousePicture picture = new HousePicture();
            picture.setId(idWorker.nextId());
            picture.setHouseId(houseId);
            picture.setCdnPrefix(cdnPrefix);
            picture.setPath(e.getPath());
            picture.setWidth(e.getWidth());
            picture.setHeight(e.getHeight());
            pictureMapper.insert(picture);
        });
    }

    public void deletePicture(Long houseId) {
        HousePictureExample housePictureExample = new HousePictureExample();
        housePictureExample.createCriteria()
                .andHouseIdEqualTo(houseId);
        pictureMapper.deleteByExample(housePictureExample);
    }

}
