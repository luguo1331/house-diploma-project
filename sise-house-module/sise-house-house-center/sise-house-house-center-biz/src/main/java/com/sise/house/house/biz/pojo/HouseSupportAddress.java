package com.sise.house.house.biz.pojo;

public class HouseSupportAddress {
    private Long id;

    private String belongTo;

    private String enName;

    private String cnName;

    private String level;

    private Double baiduMapLng;

    private Double baiduMapLat;

    public HouseSupportAddress(Long id, String belongTo, String enName, String cnName, String level, Double baiduMapLng, Double baiduMapLat) {
        this.id = id;
        this.belongTo = belongTo;
        this.enName = enName;
        this.cnName = cnName;
        this.level = level;
        this.baiduMapLng = baiduMapLng;
        this.baiduMapLat = baiduMapLat;
    }

    public HouseSupportAddress() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo == null ? null : belongTo.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Double getBaiduMapLng() {
        return baiduMapLng;
    }

    public void setBaiduMapLng(Double baiduMapLng) {
        this.baiduMapLng = baiduMapLng;
    }

    public Double getBaiduMapLat() {
        return baiduMapLat;
    }

    public void setBaiduMapLat(Double baiduMapLat) {
        this.baiduMapLat = baiduMapLat;
    }
}