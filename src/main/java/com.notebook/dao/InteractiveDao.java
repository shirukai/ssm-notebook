package com.notebook.dao;

import com.notebook.entity.Comment;
import com.notebook.entity.Interactive;

import java.util.List;

public interface InteractiveDao {
    int insertInteractive(Interactive interactive);

    int deleteInteractiveBySender(String senderId);

    int deleteInteractiveByAnswer(String answerId);

    int deleteInteractiveByCid(String cid);
}
