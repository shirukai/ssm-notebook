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

    /**
     * 获取所有用户的公开日志
     */
    @RequestMapping(value = "/findAllList")
    public Map findAllList(
            @RequestParam(value = "start", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "length", required = false, defaultValue = "10") int limit
    ) {
        List types = new ArrayList();
        types = bookService.getPublicBook(offset, limit);
        return dataTableVO(types);
    }

    private static Map dataTableVO(List list){
        Map<String, Object> map = new HashMap<String, Object>();
        //获取数据总量
        int dataTotal = list.size();
        map.put("aaData", list);
        map.put("iTotalDisplayRecords", dataTotal);
        map.put("iTotalRecords", dataTotal);
        return map;
    }
}
