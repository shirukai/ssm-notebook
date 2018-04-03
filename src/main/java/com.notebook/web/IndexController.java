package com.notebook.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/notebook")
    public String notebook() {
        return "index";
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
