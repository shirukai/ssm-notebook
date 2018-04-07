package com.notebook.service;

import com.notebook.entity.Comment;
import com.notebook.entity.Interactive;

import java.util.List;

public interface CommentService {
    int insertComment(Comment comment);

    List<Comment> getCommentByBid(String bid);

    int deleteCommentBySender(String senderId);

    int deleteCommentByAnswer(String answerId);

    int deleteCommentByCid(String cid);

    int addLikeNumber(String cid);

    int insertInteractive(Interactive interactive);

    int deleteInteractive(String iid);

}
