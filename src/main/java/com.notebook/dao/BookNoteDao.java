package com.notebook.dao;

import com.notebook.entity.BookNote;
import com.notebook.entity.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookNoteDao {
    int insertBook(BookNote bookNote);

    int updateBook(BookNote bookNote);

    int deleteBook(String bid);

    int updateBookByType(@Param("oldType") String oldType, @Param("newType") String newType);

    BookNote getBookDetailByBid(String bid);

    List<BookNote> getBookByUid(@Param("uid") String uid, @Param("offset") int offset, @Param("limit") int limit);

    List<BookNote> getBookByTid(@Param("tid") String tid, @Param("offset") int offset, @Param("limit") int limit);

    List<BookNote> getBookByReg(@Param("uid") String uid, @Param("reg") String reg, @Param("offset") int offset, @Param("limit") int limit);

    List<BookNote> getPublicBook(@Param("offset") int offset, @Param("limit") int limit);
}
