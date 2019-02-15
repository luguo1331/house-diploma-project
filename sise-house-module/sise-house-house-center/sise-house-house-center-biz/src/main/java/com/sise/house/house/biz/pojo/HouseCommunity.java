package com.sise.house.house.biz.pojo;

public class HouseCommunity {
    private Long id;

    private String cityCode;

    private String name;

    private String cityName;

    public HouseCommunity(Long id, String cityCode, String name, String cityName) {
        this.id = id;
        this.cityCode = cityCode;
        this.name = name;
        this.cityName = cityName;
    }

    public HouseCommunity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }
}