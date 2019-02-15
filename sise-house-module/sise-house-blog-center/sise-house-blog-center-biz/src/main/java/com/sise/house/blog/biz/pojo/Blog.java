package com.sise.house.blog.biz.pojo;

import java.util.Date;

public class Blog {
    private Long id;

    private Long userId;

    private String tags;

    private Date createTime;

    private String title;

    private Long cat;

    private String content;

    public Blog(Long id, Long userId, String tags, Date createTime, String title, Long cat, String content) {
        this.id = id;
        this.userId = userId;
        this.tags = tags;
        this.createTime = createTime;
        this.title = title;
        this.cat = cat;
        this.content = content;
    }

    public Blog() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getCat() {
        return cat;
    }

    public void setCat(Long cat) {
        this.cat = cat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}