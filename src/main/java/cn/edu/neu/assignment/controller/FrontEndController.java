package cn.edu.neu.assignment.controller;

import cn.edu.neu.assignment.inter.CompetitionRepository;
import cn.edu.neu.assignment.inter.DelegationRepository;
import cn.edu.neu.assignment.inter.TeamRepository;
import cn.edu.neu.assignment.inter.TypeRepository;
import cn.edu.neu.assignment.model.Competition;
import cn.edu.neu.assignment.model.Delegation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class FrontEndController {

    @Autowired
    CompetitionRepository competitionRepository;
    @Autowired
    DelegationRepository delegationRepository;
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    TeamRepository teamRepository;

    private List<Delegation> getRankedDelegations() {
        List<Delegation> delegations = delegationRepository.findAll();
        Collections.sort(delegations);
        return delegations;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("list", competitionRepository.findAll(new PageRequest(0, 6)));
        List<Delegation> delegations = getRankedDelegations();
        Collections.sort(delegations);
        delegations = delegations.subList(0, 5);
        model.addAttribute("rank", delegations);
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
    public String medal(Model model) {
        model.addAttribute("rank", (getRankedDelegations()));
        return "medal";
    }

    @RequestMapping("/competition/all")
    public String competition() {
        return "competition-all";
    }

    @RequestMapping("/competition/{id}") // Competition Type (sport, not session)
    public String competition(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("sport", typeRepository.findById(id).get());
        return "competition-detail";
    }

    @RequestMapping("/delegation/all")
    public String delegationAll() {
        return "delegation-all";
    }

    @RequestMapping("/delegation/{id}")
    public String delegation(@PathVariable Integer id, Model model) {
        model.addAttribute("delegation", delegationRepository.findById(id).get());
        return "delegation-detail";
    }

    @RequestMapping("team/{id}")
    public String teamDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("team", teamRepository.findById(id).get());
        return "team-detail";
    }

    @RequestMapping("/athlete/{name}")
    public String athlete(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
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

    @RequestMapping("/session/{id}") // Competition Item(session)
    public String session(@PathVariable Integer id, Model model) {
        Optional<Competition> competition = competitionRepository.findById(id);
        if (competition.isPresent() && (competition.get().getType() != null)) {
            model.addAttribute("session", competition.get());
            model.addAttribute("type", competition.get().getType());
            return "session-detail";
        } else {
            return "index";
        }
    }

    @RequestMapping("/session/all")
    public String session() {
        return "competition-all";
    }

    @RequestMapping("/admin/")
    public String adminIndex() {
        return "admin/index";
    }
}
