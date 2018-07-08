package cn.edu.neu.assignment.controller;

import cn.edu.neu.assignment.inter.*;
import cn.edu.neu.assignment.model.*;
import cn.edu.neu.assignment.utl.CommonUtil;
import cn.edu.neu.assignment.utl.constants.ErrorEnum;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author: CCM 20164969
 * The request controller to respond request from admin user.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IndividualRepository individualRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    CompetitionRepository competitionRepository;
    @Autowired
    IndividualCompetitionRepository individualCompetitionRepository;
    @Autowired
    TeamCompetitionRepository teamCompetitionRepository;

    /**
     * Update the information and participants of a competition
     * @param id The id of competition
     * @param postJsonObject The data want to change
     * @return The result of update
     */
    @PostMapping("competition/update/{id}")
    public JSONObject competitionUpdate(@PathVariable(value = "id") Integer id, @RequestBody JSONObject postJsonObject) {
        //Determine whether the id exists
        if (id != -1) {
            if (!competitionRepository.existsById(id)) {
                return CommonUtil.errorJson(ErrorEnum.E_503);
            }
        } else {
            id = null;
        }
        //Parse the requested parameters
        Competition competition = JSONObject.parseObject(postJsonObject.getJSONObject("competition").toJSONString(), Competition.class);
        List<Integer> participants = JSONObject.parseArray(postJsonObject.getJSONArray("participants").toJSONString(), Integer.class);
        JSONObject jsonObject = new JSONObject();
        //Refresh the information of competition
        competitionRepository.saveAndFlush(competition);
        //Determine whether the participant of competition are is individual of team
        if (competition.isIndividual()) {//individual participant
            individualCompetitionRepository.deleteAllByCompetition_Id(id);

            Iterator<Integer> i = participants.iterator();
            int participantId;


            while (i.hasNext()) {
                participantId = i.next();
                IndividualCompetition individualCompetition = new IndividualCompetition();
                if (individualRepository.existsById(participantId)) {
                    individualCompetition.setCompetition(competitionRepository.findById(id).get());
                    individualCompetition.setIndividual(individualRepository.findById(participantId).get());
                    individualCompetitionRepository.saveAndFlush(individualCompetition);
                }
            }
            //Put the new participants into response data
            jsonObject.put("participants", individualCompetitionRepository.findAllByCompetition_Id(id));
        } else {//team participant
            teamCompetitionRepository.deleteAllByCompetition_Id(id);

            Iterator<Integer> i = participants.iterator();
            int participantId;

            while (i.hasNext()) {
                participantId = i.next();
                TeamCompetition teamCompetition = new TeamCompetition();
                if (teamRepository.existsById(participantId)) {
                    teamCompetition.setCompetition(competitionRepository.findById(id).get());
                    teamCompetition.setTeam(teamRepository.findById(participantId).get());
                    teamCompetitionRepository.saveAndFlush(teamCompetition);
                }
            }
            //Put the new participants into response data
            jsonObject.put("participants", teamCompetitionRepository.findAllByCompetition_Id(id));

        }
        jsonObject.put("competition", competitionRepository.findById(id).get());
        return CommonUtil.successJson(jsonObject);
    }

    /**
     * Update result of a individual competition
     * @param id The id of competition
     * @param jsonObject The result want to update
     * @return The result after update
     */
    @PostMapping("competition/individual/result/{id}")
    public JSONObject individualResultUpdate(@PathVariable("id") Integer id,@RequestBody JSONObject jsonObject){
        List<IndividualCompetition> result = JSONObject.parseArray(jsonObject.getJSONArray("result").toJSONString(),IndividualCompetition.class);
        if (!competitionRepository.existsById(id)||!competitionRepository.findById(id).get().isIndividual())
            return CommonUtil.errorJson(ErrorEnum.E_503);
        Iterator<IndividualCompetition> i = result.iterator();
        while (i.hasNext())
            individualCompetitionRepository.saveAndFlush(i.next());
        return CommonUtil.successJson();
    }

    /**
     * Update result of a team competition
     * @param id The id of competition
     * @param jsonObject The result want to update
     * @return The result after update
     */
    @PostMapping("competition/team/result/{id}")
    public JSONObject teamResultUpdate(@PathVariable("id") Integer id,@RequestBody JSONObject jsonObject){
        List<TeamCompetition> result = JSONObject.parseArray(jsonObject.getJSONArray("result").toJSONString(),TeamCompetition.class);
        if (!competitionRepository.existsById(id)||competitionRepository.findById(id).get().isIndividual())
            return CommonUtil.errorJson(ErrorEnum.E_503);
        Iterator<TeamCompetition> i = result.iterator();
        while (i.hasNext())
            teamCompetitionRepository.saveAndFlush(i.next());
        return CommonUtil.successJson();
    }
}
