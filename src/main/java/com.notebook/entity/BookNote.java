package com.notebook.entity;

import java.util.Date;

public class BookNote {
    private String bid;
    private String uid;
    private String nickName;
    private String avatar;
    private String tid;
    private String type;
    private String bookTitle;
    private String bookContent;
    private Integer viewNumber;
    private Integer likeNumber;
    private Integer isPublic;
    private Date createTime;
    private Date modifyTime;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookContent() {
        return bookContent;
    }

    public void setBookContent(String bookContent) {
        this.bookContent = bookContent;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "BookNote{" +
                "bid='" + bid + '\'' +
                ", uid='" + uid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", tid='" + tid + '\'' +
                ", type='" + type + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookContent='" + bookContent + '\'' +
                ", viewNumber=" + viewNumber +
                ", likeNumber=" + likeNumber +
                ", isPublic=" + isPublic +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
