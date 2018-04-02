package com.notebook.web;

import com.notebook.entity.BookNote;
import com.notebook.entity.Type;
import com.notebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.notebook.dto.ResData.resData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/bookByUid")
    public Map getBookByUid(
            @RequestParam(value = "uid", required = false) String uid,
            @RequestParam(value = "start", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "length", required = false, defaultValue = "10") int limit
    ) {
        List books = bookService.getBooksByUid(uid, offset, limit);
        return dataTableVO(books);
    }

    @RequestMapping(value = "/bookByTid")
    public Map getBookByTid(
            @RequestParam(value = "tid", required = false) String tid,
            @RequestParam(value = "start", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "length", required = false, defaultValue = "10") int limit
    ) {
        List books = bookService.getBooksByType(tid, offset, limit);
        return dataTableVO(books);
    }

    @RequestMapping(value = "/bookByReg")
    public Map getBookByReg(
            @RequestParam(value = "uid", required = false) String uid,
            @RequestParam(value = "reg", required = false) String reg,
            @RequestParam(value = "start", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "length", required = false, defaultValue = "10") int limit
    ) {
        List books = bookService.getBooksByReg(uid, reg, offset, limit);
        return dataTableVO(books);
    }

    @RequestMapping(value = "/insert")
    public Map insertBook(BookNote bookNote) {
        return resData(bookService.insertBook(bookNote));
    }

    @RequestMapping(value = "/update")
    public Map updateBook(BookNote bookNote) {
        return resData(bookService.updateBook(bookNote));
    }

    @RequestMapping(value = "/delete")
    public Map deleteBook(
            @RequestParam(value = "bid", required = false) String bid
    ) {
        return resData(bookService.deleteBook(bid));
    }

    @RequestMapping(value = "/bookDetail")
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

    @RequestMapping(value = "/type/all")
    public Map getTypesByUid(
            @RequestParam(value = "uid", required = false) String uid
    ) {
        List types = bookService.getTypesByUid(uid);
        return dataTableVO(types);
    }

    @RequestMapping(value = "/type/insert")
    public Map insertType(Type type) {
        return resData(bookService.insertType(type));
    }

    @RequestMapping(value = "/type/delete")
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
