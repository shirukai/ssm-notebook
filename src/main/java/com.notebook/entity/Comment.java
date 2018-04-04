package com.notebook.entity;

import java.util.Date;

public class Comment {
    private String cid;
    private String senderUid;
    private String senderName;
    private String senderAvatar;
    private String answerUid;
    private String answerName;
    private String answerAvatar;
    private String bid;
    private String nickName;
    private String content;
    private Integer isPublic;
    private Integer likeName;
    private Date createTime;
    private Date modifyTime;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSenderAvatar() {
        return senderAvatar;
    }

    public void setSenderAvatar(String senderAvatar) {
        this.senderAvatar = senderAvatar;
    }

    public String getAnswerAvatar() {
        return answerAvatar;
    }

    public void setAnswerAvatar(String answerAvatar) {
        this.answerAvatar = answerAvatar;
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

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getLikeName() {
        return likeName;
    }

    public void setLikeName(Integer likeName) {
        this.likeName = likeName;
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
        return "Comment{" +
                "cid='" + cid + '\'' +
                ", senderUid='" + senderUid + '\'' +
                ", senderName='" + senderName + '\'' +
                ", answerUid='" + answerUid + '\'' +
                ", answerName='" + answerName + '\'' +
                ", bid='" + bid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", content='" + content + '\'' +
                ", isPublic=" + isPublic +
                ", likeName=" + likeName +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
