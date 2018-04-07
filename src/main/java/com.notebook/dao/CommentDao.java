package com.notebook.dao;

import com.notebook.entity.Comment;

import java.util.List;

public interface CommentDao {
    int insertComment(Comment comment);

    List<Comment> getCommentByBid(String bid);

    int deleteCommentBySender(String senderId);

    int deleteCommentByAnswer(String answerId);

    int deleteCommentByCid(String cid);

    int addLikeNumber(String cid);

}
