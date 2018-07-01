package cn.edu.neu.assignment.controller;

import cn.edu.neu.assignment.inter.*;
import cn.edu.neu.assignment.model.Delegation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class AdminController {

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

    private List<Delegation> getRankedDelegations() {
        List<Delegation> delegations = delegationRepository.findAll();
        Collections.sort(delegations);
        return delegations;
    }


    @RequestMapping("/admin/login")
    public String login() {
        return "admin-login";
    }

    @RequestMapping("/admin/dashboard")
    public String index(Model model) {
        return "admin-dashboard";
    }

}
