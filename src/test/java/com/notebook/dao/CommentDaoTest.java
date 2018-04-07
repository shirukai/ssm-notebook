package com.notebook.dao;

import com.notebook.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class CommentDaoTest {
    @Autowired
    CommentDao commentDao;

    @Test
    public void getCommentByBid() {
        String bid = "8f933f31-3721-11e8-966a-00163e0afc38";
        List<Comment> comments = commentDao.getCommentByBid(bid);
        comments.forEach(row->{
            System.out.println(row.getInteractives());
        });

    }
}