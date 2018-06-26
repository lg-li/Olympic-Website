package cn.edu.neu.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontEndController {

    @RequestMapping("/")
    public String index() {
        return "pages/index.html";
    }

    @RequestMapping("**/footer.html")
    public String footer() {
        return "footer";
    }

    @RequestMapping("**/header.html")
    public String header() {
        return "header";
    }

    @RequestMapping("/delegation")
    public String register() {
        return "delegation";
    }

    @RequestMapping("/delegation/all")
    public String allDeleagtion(){
        return "delegation-all";
    }

    @RequestMapping("team/detail")
    public String teamDetail(){
        return "teamDetail";
    }

}
