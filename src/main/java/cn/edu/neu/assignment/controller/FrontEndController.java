package cn.edu.neu.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontEndController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("**/footer.html")
    public String footer() {
        return "footer";
    }

    @RequestMapping("**/header.html")
    public String header() {
        return "header";
    }

    @RequestMapping("/medal")
    public String medal() {
        return "medal";
    }

    @RequestMapping("/competition/all")
    public String competition() {
        return "competition-all";
    }

    @RequestMapping("/delegation/all")
    public String delegationAll(){
        return "delegation-all";
    }

    @RequestMapping("/delegation/{name}")
    public String delegation(@PathVariable String name,Model model) {
        model.addAttribute("name",name);
        return "delegation";
    }

    @RequestMapping("team/detail")
    public String teamDetail(){
        return "team-detail";
    }

    @RequestMapping("/athlete")
    public String athlete() {
        return "athlete";
    }
}
