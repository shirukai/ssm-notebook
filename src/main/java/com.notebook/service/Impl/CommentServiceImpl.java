package com.notebook.service.Impl;

import com.notebook.dao.CommentDao;
import com.notebook.entity.Comment;
import com.notebook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    public int insertComment(Comment comment) {
        return commentDao.insertComment(comment);
    }

    public List<Comment> getCommentByBid(String bid) {
        return commentDao.getCommentByBid(bid);
    }

    public int deleteCommentBySender(String senderId) {
        return commentDao.deleteCommentBySender(senderId);
    }

    public int deleteCommentByAnswer(String answerId) {
        return commentDao.deleteCommentByAnswer(answerId);
    }
}
