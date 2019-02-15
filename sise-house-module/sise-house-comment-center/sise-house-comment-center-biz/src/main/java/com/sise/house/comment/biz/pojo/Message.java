package com.sise.house.comment.biz.pojo;

import java.util.Date;

public class Message {
    private Long id;

    private String msg;

    private Date createTime;

    private Long agentId;

    private Long houseId;

    private Long userId;

    public Message(Long id, String msg, Date createTime, Long agentId, Long houseId, Long userId) {
        this.id = id;
        this.msg = msg;
        this.createTime = createTime;
        this.agentId = agentId;
        this.houseId = houseId;
        this.userId = userId;
    }

    public Message() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}