package com.sise.house.house.biz.pojo;

public class HousePicture {
    private Integer id;

    private Long houseId;

    private String cdnPrefix;

    private Integer width;

    private Integer height;

    private String location;

    private String path;

    public HousePicture(Integer id, Long houseId, String cdnPrefix, Integer width, Integer height, String location, String path) {
        this.id = id;
        this.houseId = houseId;
        this.cdnPrefix = cdnPrefix;
        this.width = width;
        this.height = height;
        this.location = location;
        this.path = path;
    }

    public HousePicture() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getCdnPrefix() {
        return cdnPrefix;
    }

    public void setCdnPrefix(String cdnPrefix) {
        this.cdnPrefix = cdnPrefix == null ? null : cdnPrefix.trim();
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}