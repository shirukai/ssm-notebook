package com.notebook.entity;

import java.util.Date;

public class ShortNote {
    private String sid;
    private String uid;
    private String nickName;
    private String noteContent;
    private Integer viewNumber;
    private Integer likeNumber;
    private Integer isPublic;
    private Date createTime;
    private Date modifyTime;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
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

    @Override
    public String toString() {
        return "ShortNote{" +
                "sid='" + sid + '\'' +
                ", uid='" + uid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", viewNumber=" + viewNumber +
                ", likeNumber=" + likeNumber +
                ", isPublic=" + isPublic +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
