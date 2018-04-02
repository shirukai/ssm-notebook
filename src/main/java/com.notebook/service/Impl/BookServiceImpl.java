package com.notebook.service.Impl;

import com.notebook.dao.BookNoteDao;
import com.notebook.dao.TypeDao;
import com.notebook.entity.BookNote;
import com.notebook.entity.Type;
import com.notebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookNoteDao bookNoteDao;
    @Autowired
    TypeDao typeDao;

    public int insertBook(BookNote bookNote) {
        return bookNoteDao.insertBook(bookNote);
    }

    public int updateBook(BookNote bookNote) {
        return bookNoteDao.updateBook(bookNote);
    }

    public int deleteBook(String bid) {
        return bookNoteDao.deleteBook(bid);
    }

    public BookNote getBookDetailByBid(String bid) {
        return bookNoteDao.getBookDetailByBid(bid);
    }


    public List<BookNote> getBooksByUid(String uid, int offset, int limit) {
        return bookNoteDao.getBookByUid(uid, offset, limit);
    }

    public List<BookNote> getBooksByType(String tid, int offset, int limit) {
        return bookNoteDao.getBookByTid(tid, offset, limit);
    }

    public List<BookNote> getBooksByReg(String uid, String reg, int offset, int limit) {
        return bookNoteDao.getBookByReg(uid, reg, offset, limit);
    }

    public int insertType(Type type) {
        return typeDao.insertType(type);
    }

    public int updateType(Type type) {
        return 0;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public int deleteType(String tid, String uid) throws Exception {
        int res = 0;
        String noTypeName = "未分类";
        //获取未分类的type
        Type type = typeDao.getNoType(noTypeName, uid);
        //获取要删除分类的笔记数量
        int bookNumber = (bookNoteDao.getBookByTid(tid, 0, 25)).size();
        //判断type是否为空
        if (type != null) {
            // 判断tid是否为未分类的id
            if (tid.equals(type.getTid())) {
                return 0;
            }
        } else if (bookNumber > 0) {
            type = new Type();
            type.setType(noTypeName);
            type.setUid(uid);
            res = typeDao.insertType(type);
            if (res == 0) {
                throw new Exception("新增未分类失败");
            }
        }
        //删除分类
        res = typeDao.deleteType(tid);
        if (res == 0) {
            throw new Exception("删除分类失败");
        }
        if (bookNumber > 0) {
            String noTypeId = type.getTid();
            //更新相应笔记的tid
            res = bookNoteDao.updateBookByType(tid, noTypeId);
            if (res == 0) {
                throw new Exception("更新笔记失败");
            }
        }
        return res;
    }

    public List<Type> getTypesByUid(String uid) {
        return typeDao.getTypeByUid(uid);
    }
}
