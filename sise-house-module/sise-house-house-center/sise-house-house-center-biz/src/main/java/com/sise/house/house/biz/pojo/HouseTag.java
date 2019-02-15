package com.sise.house.house.biz.pojo;

public class HouseTag {
    private Long id;

    private Long houseId;

    private String name;

    public HouseTag(Long id, Long houseId, String name) {
        this.id = id;
        this.houseId = houseId;
        this.name = name;
    }

    public HouseTag() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}