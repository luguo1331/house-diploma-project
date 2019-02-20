package com.sise.house.house.biz.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sise.common.exception.MyException;
import com.sise.common.exception.ResultEnum;
import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.constants.Level;
import com.sise.house.house.api.dto.response.BaiduMapLocationRespDto;
import com.sise.house.house.api.dto.response.SubwayRespDto;
import com.sise.house.house.api.dto.response.SubwayStationRespDto;
import com.sise.house.house.api.dto.response.SupportAddressRespDto;
import com.sise.house.house.biz.dao.HouseSubwayMapper;
import com.sise.house.house.biz.dao.HouseSubwayStationMapper;
import com.sise.house.house.biz.dao.HouseSupportAddressMapper;
import com.sise.house.house.biz.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 支持地址服务层
 * @Auther: 冲之
 * @Date: 2019/2/18 15:23
 */
@Service
@Slf4j
public class SupportAddressService {

    @Resource
    private ModelMapper modelMapper;
    @Resource
    private HouseSupportAddressMapper supportMapper;
    @Resource
    private HouseSubwayMapper subwayMapper;
    @Resource
    private HouseSubwayStationMapper stationMapper;
    @Resource
    private ObjectMapper objectMapper;

    private static final String BAIDU_MAP_KEY = "6QtSF673D1pYl3eQkEXfwp8ZgsQpB77U";

    private static final String BAIDU_MAP_GEOCONV_API = "http://api.map.baidu.com/geocoder/v2/?";

    /**
     * POI数据管理接口
     */
    private static final String LBS_CREATE_API = "http://api.map.baidu.com/geodata/v3/poi/create";

    private static final String LBS_QUERY_API = "http://api.map.baidu.com/geodata/v3/poi/list?";

    private static final String LBS_UPDATE_API = "http://api.map.baidu.com/geodata/v3/poi/update";

    private static final String LBS_DELETE_API = "http://api.map.baidu.com/geodata/v3/poi/delete";

    /**
     * 获取所有支持的城市列表
     *
     * @return
     */
    public List<SupportAddressRespDto> findAllCities() {
        List<SupportAddressRespDto> result = new ArrayList<SupportAddressRespDto>();
        HouseSupportAddressExample houseSupportAddressExample = new HouseSupportAddressExample();
        houseSupportAddressExample.createCriteria().andLevelEqualTo(Level.CITY.getValue());
        return getSupportAddressRespDTOS(result, houseSupportAddressExample);
    }


    /**
     * 根据城市简写、地区简写获取具体区域的信息
     *
     * @param cityEnName
     * @param regionEnName
     * @return
     */
    public Map<Level, SupportAddressRespDto> findCityAndRegion(String cityEnName, String regionEnName) {
        Map<Level, SupportAddressRespDto> result = new HashMap();
        SupportAddressRespDto city = findCityByEnNameAndLevel(cityEnName);
        SupportAddressRespDto region = findRegionByRegionNameAndLevel(regionEnName, city.getEnName());
        result.put(Level.CITY, city);
        result.put(Level.REGION, region);
        return result;
    }

    /**
     * 根据城市英文简写获取该城市所有支持的区域信息
     *
     * @param cityName
     * @return
     */
    public List<SupportAddressRespDto> findAllRegionsByCityName(String cityName) {
        List<SupportAddressRespDto> result = new ArrayList<SupportAddressRespDto>();
        HouseSupportAddressExample houseSupportAddressExample = new HouseSupportAddressExample();
        houseSupportAddressExample.createCriteria()
                .andBelongToEqualTo(cityName)
                .andLevelEqualTo(Level.REGION.getValue());
        return getSupportAddressRespDTOS(result, houseSupportAddressExample);
    }


    /**
     * 根据行政级别、城市、查询城市
     *
     * @param cityEnName
     * @return
     */
    public SupportAddressRespDto findCityByEnNameAndLevel(String cityEnName) {
        SupportAddressRespDto result = new SupportAddressRespDto();
        HouseSupportAddressExample houseSupportAddressExample = new HouseSupportAddressExample();
        houseSupportAddressExample.createCriteria()
                .andLevelEqualTo(Level.CITY.getValue())
                .andEnNameEqualTo(cityEnName);
        return getSupportAddressRespDTO(result, houseSupportAddressExample);
    }

    /**
     * 根据行政级别、城市名、地区名查询地区
     *
     * @param regionEnName
     * @param cityEnName
     * @return
     */
    public SupportAddressRespDto findRegionByRegionNameAndLevel(String regionEnName, String cityEnName) {
        SupportAddressRespDto result = new SupportAddressRespDto();
        HouseSupportAddressExample houseSupportAddressExample = new HouseSupportAddressExample();
        houseSupportAddressExample.createCriteria()
                .andLevelEqualTo(Level.REGION.getValue())
                .andEnNameEqualTo(regionEnName)
                .andBelongToEqualTo(cityEnName);
        return getSupportAddressRespDTO(result, houseSupportAddressExample);
    }



    /**
     * 获取该城市所有的地铁线路
     *
     * @param cityEnName
     * @return
     */
    public List<SubwayRespDto> findAllSubwayByCity(String cityEnName) {
        List<SubwayRespDto> result = new ArrayList<SubwayRespDto>();
        HouseSubwayExample subwayExample = new HouseSubwayExample();
        subwayExample.createCriteria().andCityEnNameEqualTo(cityEnName);
        List<HouseSubway> houseSubways = subwayMapper.selectByExample(subwayExample);
        if (!houseSubways.isEmpty()) {
            result = modelMapper.map(houseSubways, new TypeToken<List<SubwayRespDto>>() {
            }.getRawType());
        }
        return result;
    }

    /**
     * 获取地铁线信息
     *
     * @param subwayId
     * @return
     */
    public SubwayRespDto findSubway(Long subwayId) {
        HouseSubway houseSubway = subwayMapper.selectByPrimaryKey(subwayId);
        return modelMapper.map(houseSubway, SubwayRespDto.class);
    }

    /**
     * 获取地铁线路所有的站点
     *
     * @param subwayId
     * @return
     */
    public List<SubwayStationRespDto> findAllStationBySubway(Long subwayId) {
        List<SubwayStationRespDto> result = new ArrayList<SubwayStationRespDto>();
        HouseSubwayStationExample stationExample = new HouseSubwayStationExample();
        stationExample.createCriteria().andSubwayIdEqualTo(subwayId);
        List<HouseSubwayStation> houseSubwayStations = stationMapper.selectByExample(stationExample);
        if (!houseSubwayStations.isEmpty()) {
            return modelMapper.map(houseSubwayStations, new TypeToken<List<SubwayStationRespDto>>() {
            }.getType());
        }
        return result;
    }


    /**
     * 获取地铁站点信息
     *
     * @param stationId
     * @return
     */
    public SubwayStationRespDto findSubwayStation(Long stationId) {
        HouseSubwayStation houseSubwayStation = stationMapper.selectByPrimaryKey(stationId);
        return modelMapper.map(houseSubwayStation, SubwayStationRespDto.class);
    }

    /**
     * 根据城市以及具体地位获取百度地图的经纬度
     *
     * @param city
     * @param address
     * @return
     */
    public BaiduMapLocationRespDto getBaiduMapLocation(String city, String address) {
        String encodeAddress;
        String encodeCity;

        try {
            encodeAddress = URLEncoder.encode(address, "UTF-8");
            encodeCity = URLEncoder.encode(city, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new MyException(2, "Error to encode hosue address");
        }
        HttpClient httpClient = HttpClients.createDefault();
        StringBuilder sb = new StringBuilder(BAIDU_MAP_GEOCONV_API);
        sb.append("address=").append(encodeAddress).append("&")
                .append("city=").append(encodeCity).append("&")
                .append("output=json&")
                .append("ak=").append(BAIDU_MAP_KEY);
        HttpGet get = new HttpGet(sb.toString());
        try {
            HttpResponse response = httpClient.execute(get);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new MyException(2, "Can not get baidu map location");
            }
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            JsonNode jsonNode = objectMapper.readTree(result);
            int status = jsonNode.get("status").asInt();
            if (status != 0) {
                throw new MyException(2, "Error to get map location for status:" + status);
            }
            BaiduMapLocationRespDto location = new BaiduMapLocationRespDto();
            JsonNode jsonLocation = jsonNode.get("result").get("location");
            location.setLongitude(jsonLocation.get("lng").asDouble());
            location.setLatitude(jsonLocation.get("lat").asDouble());
            return location;
        } catch (IOException e) {
            log.error("Error to fetch baidumap api", e);
            throw new MyException(2, "Error to fetch baidumap api");
        }
    }

    /**
     * 上传百度LBS数据
     *
     * @param location
     * @param title
     * @param address
     * @param houseId
     * @param price
     * @param area
     * @return
     */
    public ServerResponse lbsUpload(BaiduMapLocationRespDto location, String title,
                                    String address,
                                    long houseId, int price,
                                    int area) {
        HttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> nvps = new ArrayList();
        nvps.add(new BasicNameValuePair("latitude", String.valueOf(location.getLatitude())));
        nvps.add(new BasicNameValuePair("longitude", String.valueOf(location.getLongitude())));
        nvps.add(new BasicNameValuePair("coord_type", "3"));
        nvps.add(new BasicNameValuePair("geotable_id", "175730"));
        nvps.add(new BasicNameValuePair("ak", BAIDU_MAP_KEY));
        nvps.add(new BasicNameValuePair("houseId", String.valueOf(houseId)));
        nvps.add(new BasicNameValuePair("price", String.valueOf(price)));
        nvps.add(new BasicNameValuePair("area", String.valueOf(area)));
        nvps.add(new BasicNameValuePair("title", title));
        nvps.add(new BasicNameValuePair("address", address));

        HttpPost post;
        if (isLbsDataExists(houseId)) {
            post = new HttpPost(LBS_UPDATE_API);
        } else {
            post = new HttpPost(LBS_CREATE_API);
        }
        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse response = httpClient.execute(post);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.error("Can not upload lbs data for response: " + result);
                throw new MyException(2, "Can not upload baidu lbs data");
            } else {
                JsonNode jsonNode = objectMapper.readTree(result);
                int status = jsonNode.get("status").asInt();
                if (status != 0) {
                    String message = jsonNode.get("message").asText();
                    log.error("Error to upload lbs data for status: {}, and message: {}", status, message);
                    throw new MyException(2, "Error to upload lbs data");
                } else {
                    return ServerResponse.createBySuccess();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }

    private boolean isLbsDataExists(Long houseId) {
        HttpClient httpClient = HttpClients.createDefault();
        StringBuilder sb = new StringBuilder(LBS_QUERY_API);
        sb.append("geotable_id=").append("175730").append("&")
                .append("ak=").append(BAIDU_MAP_KEY).append("&")
                .append("houseId=").append(houseId).append(",").append(houseId);
        HttpGet get = new HttpGet(sb.toString());
        try {
            HttpResponse response = httpClient.execute(get);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.error("Can not get lbs data for response: " + result);
                return false;
            }

            JsonNode jsonNode = objectMapper.readTree(result);
            int status = jsonNode.get("status").asInt();
            if (status != 0) {
                log.error("Error to get lbs data for status: " + status);
                return false;
            } else {
                long size = jsonNode.get("size").asLong();
                if (size > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除百度LBS数据
     *
     * @param houseId
     * @return
     */
    public ServerResponse removeLbs(Long houseId) {
        HttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> nvps = new ArrayList();
        nvps.add(new BasicNameValuePair("geotable_id", "175730"));
        nvps.add(new BasicNameValuePair("ak", BAIDU_MAP_KEY));
        nvps.add(new BasicNameValuePair("houseId", String.valueOf(houseId)));

        HttpPost delete = new HttpPost(LBS_DELETE_API);
        try {
            delete.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse response = httpClient.execute(delete);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.error("Error to delete lbs data for response: " + result);
                return ServerResponse.createByError();
            }

            JsonNode jsonNode = objectMapper.readTree(result);
            int status = jsonNode.get("status").asInt();
            if (status != 0) {
                String message = jsonNode.get("message").asText();
                log.error("Error to delete lbs data for message: " + message);
                throw new MyException(2, "Error to delete lbs data for: " + message);
            }
            return ServerResponse.createBySuccess();
        } catch (IOException e) {
            log.error("Error to delete lbs data.", e);
            return ServerResponse.createByError();
        }
    }

    /**
     * 包装单个结果
     *
     * @param result
     * @param houseSupportAddressExample
     * @return
     */
    private SupportAddressRespDto getSupportAddressRespDTO(SupportAddressRespDto result, HouseSupportAddressExample houseSupportAddressExample) {
        List<HouseSupportAddress> houseSupportAddresses = supportMapper.selectByExample(houseSupportAddressExample);
        if (!houseSupportAddresses.isEmpty()) {
            result = modelMapper.map(houseSupportAddresses.get(0), SupportAddressRespDto.class);
        }
        return result;
    }

    /**
     * 包装list结果
     *
     * @param result
     * @param houseSupportAddressExample
     * @return
     */
    private List<SupportAddressRespDto> getSupportAddressRespDTOS(List<SupportAddressRespDto> result, HouseSupportAddressExample houseSupportAddressExample) {
        List<HouseSupportAddress> houseSupportAddresses = supportMapper.selectByExample(houseSupportAddressExample);
        if (!houseSupportAddresses.isEmpty()) {
            result = modelMapper.map(houseSupportAddresses, new TypeToken<List<SupportAddressRespDto>>() {
            }.getType());
        }
        return result;
    }
}
