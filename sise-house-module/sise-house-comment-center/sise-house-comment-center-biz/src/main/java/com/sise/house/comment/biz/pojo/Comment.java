package com.sise.house.comment.biz.pojo;

import java.util.Date;

public class Comment {
    private Long id;

    private String content;

    private Long houseId;

    private Date createTime;

    private Long blogId;

    private Byte type;

    private Long userId;

    public Comment(Long id, String content, Long houseId, Date createTime, Long blogId, Byte type, Long userId) {
        this.id = id;
        this.content = content;
        this.houseId = houseId;
        this.createTime = createTime;
        this.blogId = blogId;
        this.type = type;
        this.userId = userId;
    }

    public Comment() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}