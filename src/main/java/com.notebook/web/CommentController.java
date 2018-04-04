package com.notebook.web;

import com.notebook.entity.Comment;
import com.notebook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.notebook.dto.ResData.resData;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/insert")
    public Map insertComment(Comment comment) {
        return resData(commentService.insertComment(comment));
    }

    @RequestMapping(value = "/getComment")
    public Map getComment(
            @RequestParam(value = "bid", required = false) String bid
    ) {
        List<Comment> commentLists = commentService.getCommentByBid(bid);
        return resData(commentLists.size(), commentLists);
    }

    @RequestMapping(value = "/deleteBySender")
    public Map deleteBySender(
            @RequestParam(value = "senderId", required = false) String senderId
    ) {
        return resData(commentService.deleteCommentBySender(senderId));
    }

    @RequestMapping(value = "/deleteByAnswer")
    public Map deleteByAnswer(
            @RequestParam(value = "answerId", required = false) String answerId
    ) {
        return resData(commentService.deleteCommentByAnswer(answerId));
    }
}
