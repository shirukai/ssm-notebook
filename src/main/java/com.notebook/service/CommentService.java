package com.notebook.service;

import com.notebook.entity.Comment;

import java.util.List;

public interface CommentService {
    int insertComment(Comment comment);

    List<Comment> getCommentByBid(String bid);

    int deleteCommentBySender(String senderId);

    int deleteCommentByAnswer(String answerId);
}
