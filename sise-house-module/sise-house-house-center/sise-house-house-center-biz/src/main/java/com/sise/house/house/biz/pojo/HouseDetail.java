package com.sise.house.house.biz.pojo;

public class HouseDetail {
    private Long id;

    private String description;

    private String layoutDesc;

    private String traffic;

    private String roundService;

    private Integer rentWay;

    private String address;

    private Long subwayLineId;

    private String subwayLineName;

    private Long subwayStationId;

    private String subwayStationName;

    private Long houseId;

    public HouseDetail(Long id, String description, String layoutDesc, String traffic, String roundService, Integer rentWay, String address, Long subwayLineId, String subwayLineName, Long subwayStationId, String subwayStationName, Long houseId) {
        this.id = id;
        this.description = description;
        this.layoutDesc = layoutDesc;
        this.traffic = traffic;
        this.roundService = roundService;
        this.rentWay = rentWay;
        this.address = address;
        this.subwayLineId = subwayLineId;
        this.subwayLineName = subwayLineName;
        this.subwayStationId = subwayStationId;
        this.subwayStationName = subwayStationName;
        this.houseId = houseId;
    }

    public HouseDetail() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getLayoutDesc() {
        return layoutDesc;
    }

    public void setLayoutDesc(String layoutDesc) {
        this.layoutDesc = layoutDesc == null ? null : layoutDesc.trim();
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic == null ? null : traffic.trim();
    }

    public String getRoundService() {
        return roundService;
    }

    public void setRoundService(String roundService) {
        this.roundService = roundService == null ? null : roundService.trim();
    }

    public Integer getRentWay() {
        return rentWay;
    }

    public void setRentWay(Integer rentWay) {
        this.rentWay = rentWay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Long getSubwayLineId() {
        return subwayLineId;
    }

    public void setSubwayLineId(Long subwayLineId) {
        this.subwayLineId = subwayLineId;
    }

    public String getSubwayLineName() {
        return subwayLineName;
    }

    public void setSubwayLineName(String subwayLineName) {
        this.subwayLineName = subwayLineName == null ? null : subwayLineName.trim();
    }

    public Long getSubwayStationId() {
        return subwayStationId;
    }

    public void setSubwayStationId(Long subwayStationId) {
        this.subwayStationId = subwayStationId;
    }

    public String getSubwayStationName() {
        return subwayStationName;
    }

    public void setSubwayStationName(String subwayStationName) {
        this.subwayStationName = subwayStationName == null ? null : subwayStationName.trim();
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }
}