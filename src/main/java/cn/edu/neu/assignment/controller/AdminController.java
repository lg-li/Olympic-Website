package cn.edu.neu.assignment.controller;

import cn.edu.neu.assignment.inter.*;
import cn.edu.neu.assignment.model.Competition;
import cn.edu.neu.assignment.model.IndividualCompetition;
import cn.edu.neu.assignment.model.TeamCompetition;
import cn.edu.neu.assignment.utl.CommonUtil;
import cn.edu.neu.assignment.utl.constants.ErrorEnum;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

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

    @GetMapping("individual/delete/{id}")
    public JSONObject individualDelete(@PathVariable(value = "id") Integer id) {
        individualRepository.deleteById(2);
        return CommonUtil.successJson();
    }

    @PostMapping("test/{id}")
    public JSONObject test(@PathVariable(value = "id") Integer id, @RequestParam("participant") Integer participant) {
        individualCompetitionRepository.deleteAllByCompetition_Id(id);
        IndividualCompetition individualCompetition = new IndividualCompetition();
        individualCompetition.setCompetition(competitionRepository.findById(id).get());
        individualCompetition.setIndividual(individualRepository.findById(participant).get());
        individualCompetitionRepository.saveAndFlush(individualCompetition);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("competition", competitionRepository.findById(id).get());
        jsonObject.put("participants", individualCompetitionRepository.findAllByCompetition_Id(id));
        return CommonUtil.successJson(jsonObject);
    }

    @PostMapping("competition/update/{id}")
    public JSONObject individualUpdate(@PathVariable(value = "id") Integer id, @RequestBody JSONObject postJsonObject) {
        if (id != -1) {
            if (!competitionRepository.existsById(id)) {
                return CommonUtil.errorJson(ErrorEnum.E_503);
            }
        } else {
            id = null;
        }
        Competition competition = JSONObject.parseObject(postJsonObject.getJSONObject("competition").toJSONString(), Competition.class);
        List<Integer> participants = JSONObject.parseArray(postJsonObject.getJSONArray("participants").toJSONString(), Integer.class);
        JSONObject jsonObject = new JSONObject();
        id = competitionRepository.saveAndFlush(competition).getId();



        if (competition.isIndividual()) {
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
            jsonObject.put("participants", individualCompetitionRepository.findAllByCompetition_Id(id));
        } else {
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
            jsonObject.put("participants", teamCompetitionRepository.findAllByCompetition_Id(id));

        }
        jsonObject.put("competition", competitionRepository.findById(id).get());
        return CommonUtil.successJson(jsonObject);
    }

    @PostMapping("competition/individual/result/{id}")
    public JSONObject individualResultUpdate(@PathVariable("id") Integer id, @RequestBody JSONObject jsonObject) {
        List<IndividualCompetition> result = JSONObject.parseArray(jsonObject.getJSONArray("result").toJSONString(), IndividualCompetition.class);
        if (!competitionRepository.existsById(id) || !competitionRepository.findById(id).get().isIndividual())
            return CommonUtil.errorJson(ErrorEnum.E_503);
        Iterator<IndividualCompetition> i = result.iterator();
        while (i.hasNext())
            individualCompetitionRepository.saveAndFlush(i.next());
        return CommonUtil.successJson();
    }

    @PostMapping("competition/team/result/{id}")
    public JSONObject teamResultUpdate(@PathVariable("id") Integer id, @RequestBody JSONObject jsonObject) {
        List<TeamCompetition> result = JSONObject.parseArray(jsonObject.getJSONArray("result").toJSONString(), TeamCompetition.class);
        if (!competitionRepository.existsById(id) || competitionRepository.findById(id).get().isIndividual())
            return CommonUtil.errorJson(ErrorEnum.E_503);
        Iterator<TeamCompetition> i = result.iterator();
        while (i.hasNext())
            teamCompetitionRepository.saveAndFlush(i.next());
        return CommonUtil.successJson();
    }
}
