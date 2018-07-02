package cn.edu.neu.assignment.controller;

import cn.edu.neu.assignment.inter.CompetitionRepository;
import cn.edu.neu.assignment.model.Competition;
import cn.edu.neu.assignment.utl.CommonUtil;
import cn.edu.neu.assignment.utl.constants.ErrorEnum;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/competition")
public class CompetitionController {
    @Autowired
    CompetitionRepository competitionRepository;

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
        JSONObject jsonObject= new JSONObject();
        Competition competition = competitionRepository.findById(id).get();
        jsonObject.put("competition",competition);
        return CommonUtil.successJson(jsonObject);
    }
}
