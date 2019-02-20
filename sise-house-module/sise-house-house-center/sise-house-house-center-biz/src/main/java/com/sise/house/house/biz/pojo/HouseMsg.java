package com.sise.house.house.biz.pojo;

import java.util.Date;

public class HouseMsg {
    private Long id;

    private String name;

    private Byte type;

    private Integer price;

    private Integer area;

    private Integer beds;

    private Integer baths;

    private Integer parlour;

    private Integer floor;

    private Integer totalFloor;

    private Integer watchTimes;

    private Integer buildYear;

    private Double rating;

    private String remarks;

    private String properties;

    private String floorPlan;

    private String cityEnName;

    private String regionEnName;

    private Long communityId;

    private String district;

    private String address;

    private String cover;

    private Integer direction;

    private Date createTime;

    private Date lastUpdateTime;

    private Integer status;

    private Integer distanceToSubway;

    private Long userId;

    private String street;

    public HouseMsg(Long id, String name, Byte type, Integer price, Integer area, Integer beds, Integer baths, Integer parlour, Integer floor, Integer totalFloor, Integer watchTimes, Integer buildYear, Double rating, String remarks, String properties, String floorPlan, String cityEnName, String regionEnName, Long communityId, String district, String address, String cover, Integer direction, Date createTime, Date lastUpdateTime, Integer status, Integer distanceToSubway, Long userId, String street) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.area = area;
        this.beds = beds;
        this.baths = baths;
        this.parlour = parlour;
        this.floor = floor;
        this.totalFloor = totalFloor;
        this.watchTimes = watchTimes;
        this.buildYear = buildYear;
        this.rating = rating;
        this.remarks = remarks;
        this.properties = properties;
        this.floorPlan = floorPlan;
        this.cityEnName = cityEnName;
        this.regionEnName = regionEnName;
        this.communityId = communityId;
        this.district = district;
        this.address = address;
        this.cover = cover;
        this.direction = direction;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
        this.status = status;
        this.distanceToSubway = distanceToSubway;
        this.userId = userId;
        this.street = street;
    }

    public HouseMsg() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Integer getBaths() {
        return baths;
    }

    public void setBaths(Integer baths) {
        this.baths = baths;
    }

    public Integer getParlour() {
        return parlour;
    }

    public void setParlour(Integer parlour) {
        this.parlour = parlour;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(Integer totalFloor) {
        this.totalFloor = totalFloor;
    }

    public Integer getWatchTimes() {
        return watchTimes;
    }

    public void setWatchTimes(Integer watchTimes) {
        this.watchTimes = watchTimes;
    }

    public Integer getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(Integer buildYear) {
        this.buildYear = buildYear;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
    }

    public String getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan == null ? null : floorPlan.trim();
    }

    public String getCityEnName() {
        return cityEnName;
    }

    public void setCityEnName(String cityEnName) {
        this.cityEnName = cityEnName == null ? null : cityEnName.trim();
    }

    public String getRegionEnName() {
        return regionEnName;
    }

    public void setRegionEnName(String regionEnName) {
        this.regionEnName = regionEnName == null ? null : regionEnName.trim();
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDistanceToSubway() {
        return distanceToSubway;
    }

    public void setDistanceToSubway(Integer distanceToSubway) {
        this.distanceToSubway = distanceToSubway;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }
}