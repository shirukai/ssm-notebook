package com.notebook.entity;

import java.util.Date;

public class Interactive {
    private String iid;
    private String senderUid;
    private String senderName;
    private String answerUid;
    private String answerName;
    private String cid;
    private String content;
    private Date createTime;
    private Date modifyTime;

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(String senderUid) {
        this.senderUid = senderUid;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getAnswerUid() {
        return answerUid;
    }

    public void setAnswerUid(String answerUid) {
        this.answerUid = answerUid;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Interactive{" +
                "iid='" + iid + '\'' +
                ", senderUid='" + senderUid + '\'' +
                ", senderName='" + senderName + '\'' +
                ", answerUid='" + answerUid + '\'' +
                ", answerName='" + answerName + '\'' +
                ", cid='" + cid + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
