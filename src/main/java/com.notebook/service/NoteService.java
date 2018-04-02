package com.notebook.service;

import com.notebook.entity.ShortNote;

import java.util.List;

public interface NoteService {
    int insertNote(ShortNote shortNote);
    int deleteBySid(String sid);
    int updateNote(ShortNote shortNote);
    int updateViewNumber();
    int updateLikeNumber(String sid);
    List<ShortNote> getAllByUid(String uid, int offset, int limit);
    List<ShortNote> getPublicNote(int offset, int limit);
}
