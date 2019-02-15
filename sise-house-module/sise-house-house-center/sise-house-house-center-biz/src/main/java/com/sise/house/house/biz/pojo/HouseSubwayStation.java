package com.sise.house.house.biz.pojo;

public class HouseSubwayStation {
    private Long id;

    private Long subwayId;

    private String name;

    public HouseSubwayStation(Long id, Long subwayId, String name) {
        this.id = id;
        this.subwayId = subwayId;
        this.name = name;
    }

    public HouseSubwayStation() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(Long subwayId) {
        this.subwayId = subwayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}