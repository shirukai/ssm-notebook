package com.notebook.service.Impl;

import com.notebook.dao.BookNoteDao;
import com.notebook.dao.CommentDao;
import com.notebook.dao.InteractiveDao;
import com.notebook.entity.Comment;
import com.notebook.entity.Interactive;
import com.notebook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;
    @Autowired
    BookNoteDao bookNoteDao;
    @Autowired
    InteractiveDao interactiveDao;

    public int insertComment(Comment comment) {
        return commentDao.insertComment(comment);
    }

    public List<Comment> getCommentByBid(String bid) {
        List<Comment> comments = commentDao.getCommentByBid(bid);
        comments.forEach(row -> {
            row.getInteractives().sort(new Comparator<Interactive>() {
                @Override
                public int compare(Interactive o1, Interactive o2) {
                    return o1.getCreateTime().compareTo(o2.getCreateTime());
                }
            });
        });
        return comments;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public int deleteCommentByCid(String cid) {
        //删除互动
        interactiveDao.deleteInteractiveByCid(cid);
        //删除评论
        int re = commentDao.deleteCommentByCid(cid);
        return re;
    }

    public int deleteCommentBySender(String senderId) {
        return commentDao.deleteCommentBySender(senderId);
    }

    public int deleteCommentByAnswer(String answerId) {
        return commentDao.deleteCommentByAnswer(answerId);
    }

    @Override
    public int insertInteractive(Interactive interactive) {
        return interactiveDao.insertInteractive(interactive);
    }

    @Override
    public int deleteInteractive(String iid) {
        return interactiveDao.deleteInteractiveBySender(iid);
    }

    @Override
    public int addLikeNumber(String cid) {
        return commentDao.addLikeNumber(cid);
    }
}
