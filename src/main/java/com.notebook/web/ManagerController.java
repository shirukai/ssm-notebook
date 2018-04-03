package com.notebook.web;

import com.notebook.entity.BookNote;
import com.notebook.entity.ShortNote;
import com.notebook.entity.Type;
import com.notebook.service.BookService;
import com.notebook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.notebook.dto.ResData.resData;

@RestController
@RequestMapping("/admin")
public class ManagerController {
    @Autowired
    BookService bookService;
    @Autowired
    NoteService noteService;

    @RequestMapping(value = "/book/bookByUid")
    public Map getBookByUid(
            @RequestParam(value = "uid", required = false) String uid,
            @RequestParam(value = "start", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "length", required = false, defaultValue = "10") int limit
    ) {
        List books = bookService.getBooksByUid(uid, offset, limit);
        return dataTableVO(books);
    }

    @RequestMapping(value = "/book/bookByTid")
    public Map getBookByTid(
            @RequestParam(value = "tid", required = false) String tid,
            @RequestParam(value = "start", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "length", required = false, defaultValue = "10") int limit
    ) {
        List books = bookService.getBooksByType(tid, offset, limit);
        return dataTableVO(books);
    }

    @RequestMapping(value = "/book/bookByReg")
    public Map getBookByReg(
            @RequestParam(value = "uid", required = false) String uid,
            @RequestParam(value = "reg", required = false) String reg,
            @RequestParam(value = "start", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "length", required = false, defaultValue = "10") int limit
    ) {
        List books = bookService.getBooksByReg(uid, reg, offset, limit);
        return dataTableVO(books);
    }

    @RequestMapping(value = "/book/insert")
    public Map insertBook(BookNote bookNote) {
        return resData(bookService.insertBook(bookNote));
    }

    @RequestMapping(value = "/book/update")
    public Map updateBook(BookNote bookNote) {
        return resData(bookService.updateBook(bookNote));
    }

    @RequestMapping(value = "/book/delete")
    public Map deleteBook(
            @RequestParam(value = "bid", required = false) String bid
    ) {
        return resData(bookService.deleteBook(bid));
    }

    @RequestMapping(value = "/book/bookDetail")
    public Map getBookDetailByBid(
            @RequestParam(value = "bid", required = false) String bid) {
        int state = 0;
        BookNote bookNote = null;
        try {
            bookNote = bookService.getBookDetailByBid(bid);
            state = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resData(state, bookNote);
    }

    @RequestMapping(value = "/book/type/all")
    public Map getTypesByUid(
            @RequestParam(value = "uid", required = false) String uid
    ) {
        List types = bookService.getTypesByUid(uid);
        return dataTableVO(types);
    }

    @RequestMapping(value = "/book/type/insert")
    public Map insertType(Type type) {
        return resData(bookService.insertType(type));
    }

    @RequestMapping(value = "/book/type/delete")
    public Map deleteType(
            @RequestParam(value = "tid", required = false) String tid,
            @RequestParam(value = "uid", required = false) String uid
    ) {
        int res = 0;
        try {
            res = bookService.deleteType(tid, uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resData(res);
    }

    @RequestMapping(value = "/note/insert")
    public Map insertNote(ShortNote shortNote) {
        int state = noteService.insertNote(shortNote);
        String sid = shortNote.getSid();
        return resData(state, sid);
    }

    @RequestMapping(value = "/note/page")
    public Map getByPage(
            @RequestParam(value = "uid", required = false) String uid,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize
    ) {
        int offset = (page - 1) * pageSize;
        List result = noteService.getAllByUid(uid, offset, pageSize);
        return resData(result.size(), result);
    }

    @RequestMapping(value = "/note/delete")
    public Map deleteNode(
            @RequestParam(value = "sid", required = false) String sid
    ) {
        return resData(noteService.deleteBySid(sid));
    }

    @RequestMapping(value = "/note/update")
    public Map updateNode(ShortNote shortNote) {
        return resData(noteService.updateNote(shortNote));
    }

    private static Map dataTableVO(List list) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取数据总量
        int dataTotal = list.size();
        map.put("aaData", list);
        map.put("iTotalDisplayRecords", dataTotal);
        map.put("iTotalRecords", dataTotal);
        return map;
    }
}
