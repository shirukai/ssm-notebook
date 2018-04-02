package com.notebook.entity;

import java.util.Date;

public class Type {
    private String tid;
    private String uid;
    private String type;
    private Date createTime;
    private Date modifyTime;
    private Integer bookNumber;

    public Integer getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Integer bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "Type{" +
                "tid='" + tid + '\'' +
                ", uid='" + uid + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", bookNumber=" + bookNumber +
                '}';
    }
}
