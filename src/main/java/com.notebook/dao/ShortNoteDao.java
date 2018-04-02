package com.notebook.dao;

import com.notebook.entity.ShortNote;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShortNoteDao {
    int insertShort(ShortNote shortNote);
    int deleteShort(String sid);
    int updateShort(ShortNote shortNote);
    int addLikeNumber(String sid);

    List<ShortNote> queryShortByUid(@Param("uid") String uid, @Param("offset") int offset, @Param("limit") int limit);
}
