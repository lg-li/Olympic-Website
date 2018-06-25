package com.neu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontEndController {

    @RequestMapping("")
    public String index() {
        return "index";
    }

    @RequestMapping("pages/{path}")
    public String register(@PathVariable() String path) {
        return  path;
    }
}
