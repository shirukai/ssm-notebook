package com.notebook.entity;

import java.util.Date;

public class Comment {
    private String cid;
    private String uid;
    private String bid;
    private String nickName;
    private String content;
    private Integer isPublic;
    private Integer viewName;
    private Integer likeName;
    private Date createTime;
    private Date modifyTime;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public Integer getViewName() {
        return viewName;
    }

    public void setViewName(Integer viewName) {
        this.viewName = viewName;
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
                ", uid='" + uid + '\'' +
                ", bid='" + bid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", content='" + content + '\'' +
                ", isPublic=" + isPublic +
                ", viewName=" + viewName +
                ", likeName=" + likeName +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
