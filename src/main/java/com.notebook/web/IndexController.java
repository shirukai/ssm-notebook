package com.notebook.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notebook")
public class IndexController {
    @RequestMapping("/{jspName}")
    public String jspPage(@PathVariable("jspName") String jspName) {
        return jspName;
    }
}
