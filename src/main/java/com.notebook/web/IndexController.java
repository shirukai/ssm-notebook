package com.notebook.web;

import com.notebook.entity.BookNote;
import com.notebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @Autowired
    BookService bookService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/notebook")
    public String notebook() {
        return "index";
    }

    @RequestMapping("/notebook/view/{bid}")
    public String getBookView(Model model, @PathVariable("bid") String bid) {
        BookNote bookNote = bookService.getBookDetailByBid(bid);
        if (bookNote.getIsPublic() != 1) {
            return "";
        }
        model.addAttribute("book", bookNote);
        return "bookDetail";
    }

    @RequestMapping("/admin/notebook/{jspName}")
    public String adminPage(@PathVariable("jspName") String jspName) {
        return jspName;
    }

    @RequestMapping("/notebook/{jspName}")
    public String jspPage(@PathVariable("jspName") String jspName) {
        return jspName;
    }
}
