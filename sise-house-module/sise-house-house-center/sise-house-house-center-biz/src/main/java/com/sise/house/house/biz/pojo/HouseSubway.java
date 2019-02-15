package com.sise.house.house.biz.pojo;

public class HouseSubway {
    private Long id;

    private String name;

    private String cityEnName;

    public HouseSubway(Long id, String name, String cityEnName) {
        this.id = id;
        this.name = name;
        this.cityEnName = cityEnName;
    }

    public HouseSubway() {
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

    public String getCityEnName() {
        return cityEnName;
    }

    public void setCityEnName(String cityEnName) {
        this.cityEnName = cityEnName == null ? null : cityEnName.trim();
    }
}