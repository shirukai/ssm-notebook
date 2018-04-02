package com.notebook.service;

import com.notebook.entity.BookNote;
import com.notebook.entity.Type;

import java.util.List;

public interface BookService {
    int insertBook(BookNote bookNote);
    int updateBook(BookNote bookNote);
    int deleteBook(String bid);
    BookNote getBookDetailByBid(String bid);
    List<BookNote> getBooksByUid(String uid,int offset,int limit);
    List<BookNote> getBooksByType(String tid,int offset,int limit);
    List<BookNote> getBooksByReg(String uid,String tid,int offset,int limit);
    int insertType(Type type);
    int updateType(Type type);
    int deleteType(String tid,String uid)throws Exception;
    List<Type> getTypesByUid(String uid);

}
