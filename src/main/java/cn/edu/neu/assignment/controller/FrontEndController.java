package cn.edu.neu.assignment.controller;

import cn.edu.neu.assignment.inter.*;
import cn.edu.neu.assignment.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

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
    @Autowired
    IndividualRepository individualRepository;
    @Autowired
    IndividualCompetitionRepository individualCompetitionRepository;
    @Autowired
    TeamCompetitionRepository teamCompetitionRepository;

    private List<Delegation> getRankedDelegations() {
        List<Delegation> delegations = delegationRepository.findAll();
        Collections.sort(delegations);
        Collections.reverse(delegations);
        return delegations;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("list", competitionRepository.findAll(new PageRequest(0, 6)));
        List<Delegation> delegations = getRankedDelegations();
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

    @RequestMapping("/sport/all")
    public String type() {
        return "sport-all";
    }

    @RequestMapping("/sport/{id}") // Competition Type (sport, not session)
    public String type(@PathVariable Integer id, Model model) {
        Competition competition = new Competition();
        competition.setType(typeRepository.findById(id).get());
        model.addAttribute("id", id);
        model.addAttribute("sport", typeRepository.findById(id).get());
        model.addAttribute("competitions",competitionRepository.findAll(Example.of(competition)));
        return "sport-detail";
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
        Team team = teamRepository.findById(id).get();
        model.addAttribute("team", team);

        Team teamForExample = new Team();
        teamForExample.setDelegations(team.getDelegations());
        TeamCompetition teamCompetitionForExample = new TeamCompetition();
        teamCompetitionForExample.setTeam(team);

       List<Team> teamList = teamRepository.findAll(Example.of(teamForExample));
        Iterator iterator = teamList.iterator();
        while (iterator.hasNext()){
            if (iterator.next()==team)
                iterator.remove();
        }
        model.addAttribute("teamCompetitions",teamCompetitionRepository.findAll(Example.of(teamCompetitionForExample)));
        model.addAttribute("teamList",teamList);
        model.addAttribute("individuals",team.getIndividuals());
        return "team-detail";
    }

    @RequestMapping("/athlete/{id}")
    public String athlete(@PathVariable Integer id, Model model) {
        Optional<Individual> individual = individualRepository.findById(id);
        IndividualCompetition individualCompetition = new IndividualCompetition();
        individualCompetition.setIndividual(individual.get());
        if(individual.isPresent()) {
            model.addAttribute("athlete",individual.get());
            model.addAttribute("competitions",individualCompetitionRepository.findAll(Example.of(individualCompetition)));
            return "athlete-detail";
        }else{
            return "index";
        }
    }

    @RequestMapping("/manage/login")
    public String login() {
        return "admin/manager-login";
    }

    @RequestMapping("/participants")
    public String participants() {
        return "participants";
    }

    @RequestMapping("/competition/{id}") // Competition Item(session)
    public String competition(@PathVariable Integer id, Model model) {
        Competition competition = competitionRepository.findById(id).get();
        model.addAttribute("participant", competition.isIndividual() ?
                competition.getIndividualCompetitions() :
                        competition.getTeamCompetitions()
        );
        if (competition.getType() != null) {
            model.addAttribute("compSession", competition);
            model.addAttribute("type", competition.getType());
            model.addAttribute("sessionName",competition.getName());
            model.addAttribute("isIndividual", competition.isIndividual());
            return "competition-session-detail";
        } else {
            return "index";
        }
    }

    @RequestMapping("/competition/all")
    public String competition() {
        return "sport-all";
    }

    @RequestMapping("/admin/")
    public String adminIndex() {
        return "admin/index";
    }
}
