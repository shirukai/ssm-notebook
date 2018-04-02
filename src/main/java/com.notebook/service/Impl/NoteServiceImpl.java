package com.notebook.service.Impl;

import com.notebook.dao.ShortNoteDao;
import com.notebook.entity.ShortNote;
import com.notebook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    ShortNoteDao shortNoteDao;

    public int insertNote(ShortNote shortNote) {
        return shortNoteDao.insertShort(shortNote);
    }

    public int deleteBySid(String sid) {
        return shortNoteDao.deleteShort(sid);
    }

    public int updateViewNumber() {
        return 0;
    }

    public int updateLikeNumber(String sid) {
        return shortNoteDao.addLikeNumber(sid);
    }

    public List<ShortNote> getAllByUid(String uid, int offset, int limit) {
        return shortNoteDao.queryShortByUid(uid, offset, limit);
    }

    public int updateNote(ShortNote shortNote) {
        return shortNoteDao.updateShort(shortNote);
    }
}
