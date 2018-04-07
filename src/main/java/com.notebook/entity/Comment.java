package com.notebook.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Comment {
    private String cid;
    private String senderUid;
    private String senderName;
    private String senderAvatar;
    private String answerUid;
    private String answerName;
    private String answerAvatar;
    private String bid;
    private String content;
    private Integer isPublic;
    private Integer likeNumber;
    private Date createTime;
    private Date modifyTime;
    private List<Interactive> interactives;

    public List<Interactive> getInteractives() {
        return interactives;
    }

    public void setInteractives(List<Interactive> interactives) {
        this.interactives = interactives;
    }


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

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeName) {
        this.likeNumber = likeName;
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
                ", senderAvatar='" + senderAvatar + '\'' +
                ", answerUid='" + answerUid + '\'' +
                ", answerName='" + answerName + '\'' +
                ", answerAvatar='" + answerAvatar + '\'' +
                ", bid='" + bid + '\'' +
                ", content='" + content + '\'' +
                ", isPublic=" + isPublic +
                ", likeNumber=" + likeNumber +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", interactives=" + interactives +
                '}';
    }
}
