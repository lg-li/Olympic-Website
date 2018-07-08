package cn.edu.neu.assignment.controller;

import cn.edu.neu.assignment.inter.*;
import cn.edu.neu.assignment.model.*;
import cn.edu.neu.assignment.utl.Jwt;
import cn.edu.neu.assignment.utl.TokenState;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author CCM 20164969 LLG 20165254
 * @Description The route of frontend and inject data
 */
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

    /**
     * The method to get the rank list of delegation
     * @return The rank list of delegation
     */
    private List<Delegation> getRankedDelegations() {
        List<Delegation> delegations = delegationRepository.findAll();
        Collections.sort(delegations);
        Collections.reverse(delegations);
        return delegations;
    }

    /**
     * The route for home page
     * @param model The data to inject into page
     * @return The html content of this page
     */
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("competitions", competitionRepository.findAll(new PageRequest(0, 6)));
        List<Delegation> delegations = getRankedDelegations();
        delegations = delegations.subList(0, 5);
        model.addAttribute("rank", delegations);
        return "index";
    }

    /**
     * The route for global footer
     * @return The html content of this page
     */
    @RequestMapping("**/footer.html")
    public String footer() {
        return "footer";
    }

    /**
     * The route for global header
     * @return The html content of this page
     */
    @RequestMapping("**/header.html")
    public String header() {
        return "header";
    }

    /**
     * The route for rank list page
     * @param model The data to inject into page
     * @return The html content of this page
     */
    @RequestMapping("/medal")
    public String rank(Model model) {
        model.addAttribute("rank", (getRankedDelegations()));
        return "medal";
    }

    /**
     * The route for sport types list page
     * @return The html content of this page
     */
    @RequestMapping("/sport/all")
    public String typeAll() {
        return "sport-all";
    }

    /**
     * The route for sport page
     * @param id The id of sport
     * @param model The data want to inject into page
     * @return The html content of this page
     */
    @RequestMapping("/sport/{id}") // Competition Type (sport, not session)
    public String type(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("sport", typeRepository.findById(id).get());
        model.addAttribute("competitions",competitionRepository.findAllByType_Id(id));
        return "sport-detail";
    }

    /**
     * The route for delegations list page
     * @return The html content of this page
     */
    @RequestMapping("/delegation/all")
    public String delegationAll() {
        return "delegation-all";
    }

    /**
     * The route for a delegation page
     * @param id The id of delegation
     * @param model The data want to inject into page
     * @return The html content of this page
     */
    @RequestMapping("/delegation/{id}")
    public String delegation(@PathVariable Integer id, Model model) {
        Delegation delegation = delegationRepository.findById(id).get();
        delegation.countMedals();
        model.addAttribute("delegation", delegation);
        model.addAttribute("individuals",individualRepository.findAllByDelegations_Id(id));
        model.addAttribute("teams",teamRepository.findAllByDelegations_Id(id));
        return "delegation-detail";
    }

    /**
     * The route for a team page
     * @param id The id of team
     * @param model The data want to inject into page
     * @return The html content of this page
     */
    @RequestMapping("team/{id}")
    public String teamDetail(@PathVariable Integer id, Model model) {
        Team team = teamRepository.findById(id).get();
        model.addAttribute("team", team);

       List<Team> teamList = teamRepository.findAllByDelegations_Id(team.getDelegations().getId());
        Iterator iterator = teamList.iterator();
        while (iterator.hasNext()){
            if (iterator.next()==team)
                iterator.remove();
        }
        model.addAttribute("teamCompetitions",teamCompetitionRepository.findAllByTeam_Id(id));
        model.addAttribute("teamList",teamList);
        model.addAttribute("individuals",team.getIndividuals());
        return "team-detail";
    }

    /**
     * The route for a athlete page
     * @param id The id of athlete
     * @param model The data want to inject into page
     * @return The html content of this page
     */
    @RequestMapping("/athlete/{id}")
    public String athlete(@PathVariable Integer id, Model model) {
        Optional<Individual> individual = individualRepository.findById(id);
        if(individual.isPresent()) {
            model.addAttribute("athlete",individual.get());
            model.addAttribute("competitions",individualCompetitionRepository.findAllByIndividual_Id(id));
            return "athlete-detail";
        }else{
            return "index";
        }
    }

    /**
     * The route for the search page
     * @param model The data want to inject into page
     * @return The html content of this page
     */
    @RequestMapping("/participants")
    public String participants(Model model) {
        model.addAttribute("delegations", delegationRepository.findAll());
        model.addAttribute("sports", typeRepository.findAll());
        return "participants";
    }

    /**
     * The route for a competition
     * @param id The id of competition
     * @param model The data want to inject into page
     * @return The html content of this page
     */
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

    /**
     * The route for all competition page
     * @return The html content of this page
     */
    @RequestMapping("/competition/all")
    public String competition() {
        return "sport-all";
    }

    /**
     * The route for login page
     * @return The html content of this page
     */
    @RequestMapping("/admin/login")
    public String login() {
        return "admin-login";
    }

    /**
     * The route for admin page
     * @param model The data want to inject into page
     * @return The html content of this page
     */
    @RequestMapping("/admin/dashboard")
    public String adminIndex(Model model, HttpServletRequest request) {
        String token = request.getParameter("token");
        if(Jwt.validToken(token).get("state").equals(TokenState.VALID.toString())) {
            model.addAttribute("athletes", individualRepository.findAll());
            model.addAttribute("teams", teamRepository.findAll());
            model.addAttribute("sports", typeRepository.findAll());
            model.addAttribute("delegations", delegationRepository.findAll());
            model.addAttribute("competitions", competitionRepository.findAll());
            return "admin-dashboard";
        } else {
            return login();
        }
    }
}
