package cn.edu.neu.assignment.controller;

import cn.edu.neu.assignment.inter.CompetitionRepository;
import cn.edu.neu.assignment.inter.IndividualCompetitionRepository;
import cn.edu.neu.assignment.inter.TeamCompetitionRepository;
import cn.edu.neu.assignment.model.*;
import cn.edu.neu.assignment.utl.CommonUtil;
import cn.edu.neu.assignment.utl.constants.ErrorEnum;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * The request controller to respond request for competition information.
 */
@RestController
@RequestMapping("/competition")
public class CompetitionController {
    @Autowired
    CompetitionRepository competitionRepository;
    @Autowired
    IndividualCompetitionRepository individualCompetitionRepository;
    @Autowired
    TeamCompetitionRepository teamCompetitionRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JSONObject list() {
        JSONObject jsonObject = new JSONObject();
        List<Competition> list = competitionRepository.findAll();
        for (Competition competition : list) {
            if (competition.getSituation() == 0) {
                if (competition.getTime().compareTo(new Date()) >= 0) {
                    competition.setSituation((short) 1);
                    competitionRepository.saveAndFlush(competition);
                }
            }
        }
        jsonObject.put("list", list);
        return CommonUtil.successJson(jsonObject);
    }

    @GetMapping(value = "/get/{id}")
    public JSONObject get(@PathVariable("id") Integer id){
        if (!competitionRepository.existsById(id))
            return CommonUtil.errorJson(ErrorEnum.E_503);
        JSONObject jsonObject= new JSONObject();
        Competition competition = competitionRepository.findById(id).get();
        if (competition.isIndividual()){
            List<Individual> individuals = new ArrayList<>();
            Iterator<IndividualCompetition> i = competition.getIndividualCompetitions().iterator();
            while (i.hasNext())
                individuals.add(i.next().getIndividual());
            jsonObject.put("participants",individuals);
        }else {
            List<Team> teams = new ArrayList<>();
            Iterator<TeamCompetition> i = competition.getTeamCompetitions().iterator();
            while (i.hasNext())
                teams.add(i.next().getTeam());
            jsonObject.put("participants",teams);
        }
        jsonObject.put("competition",competition);
        return CommonUtil.successJson(jsonObject);
    }

    @GetMapping(value = "result/{id}")
    public JSONObject result(@PathVariable("id") Integer id){
        if (!competitionRepository.existsById(id))
            return CommonUtil.errorJson(ErrorEnum.E_503);
        JSONObject jsonObject = new JSONObject();
        if (competitionRepository.findById(id).get().isIndividual()){
            jsonObject.put("result",individualCompetitionRepository.findAllByCompetition_Id(id));
            return CommonUtil.successJson(jsonObject);
        }else {
            jsonObject.put("result",teamCompetitionRepository.findAllByCompetition_Id(id));
            return CommonUtil.successJson(jsonObject);
        }
    }
}
