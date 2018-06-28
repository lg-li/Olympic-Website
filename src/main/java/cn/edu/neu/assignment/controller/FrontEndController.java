package cn.edu.neu.assignment.controller;

import cn.edu.neu.assignment.inter.CompetitionRepository;
import cn.edu.neu.assignment.inter.DelegationRepository;
import cn.edu.neu.assignment.model.Competition;
import cn.edu.neu.assignment.model.Delegation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Controller
public class FrontEndController {

    @Autowired
    CompetitionRepository competitionRepository;
    @Autowired
    DelegationRepository delegationRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("list", competitionRepository.findAll(new PageRequest(0,4)));

        List<Delegation> delegations = delegationRepository.findAll();
        Collections.sort(delegations, new Comparator<Delegation>(){
            public int compare(Delegation a1, Delegation a2) {
                int x = a1.countMedals(1) - a2.countMedals(1);
                int y = a1.countMedals(2) - a2.countMedals(2);
                int z = a1.countMedals(3) - a2.countMedals(3);
                if(x==0){
                    if(y==0){
                        return z;
                    }
                    return y;
                }
                return x;
            }

        });
        model.addAttribute("rank",delegations);
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

    @RequestMapping("/competition/{id}")
    public String competition(@PathVariable String id, Model model) {
        model.addAttribute("id",id);
        return "competition-detail";
    }

    @RequestMapping("/delegation/all")
    public String delegationAll(){
        return "delegation-all";
    }

    @RequestMapping("/delegation/{name}")
    public String delegation(@PathVariable String name, Model model) {
        model.addAttribute("name",name);
        return "delegation";
    }

    @RequestMapping("team/detail")
    public String teamDetail(){
        return "team-detail";
    }

    @RequestMapping("/athlete/{name}")
    public String athlete(@PathVariable String name, Model model) {
        model.addAttribute("name",name);
        return "athlete-detail";
    }

    @RequestMapping("/manage/login")
    public String login() {
        return "manager-login";
    }

    @RequestMapping("/participants")
    public String participants() {
        return "participants";
    }

    @RequestMapping("/session/{id}")
    public String session(@PathVariable Integer id, Model model) {
        model.addAttribute("competition",competitionRepository.findById(id));
        return "session-detail";
    }

    @RequestMapping("/session/all")
    public String session() {
        return "competition-all#Section2";
    }
}
