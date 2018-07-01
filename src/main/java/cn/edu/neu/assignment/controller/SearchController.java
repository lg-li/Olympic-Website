package cn.edu.neu.assignment.controller;

import cn.edu.neu.assignment.inter.IndividualRepository;
import cn.edu.neu.assignment.inter.TeamRepository;
import cn.edu.neu.assignment.model.Competition;
import cn.edu.neu.assignment.model.Individual;
import cn.edu.neu.assignment.utl.CommonUtil;
import cn.edu.neu.assignment.utl.constants.ErrorEnum;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    IndividualRepository individualRepository;
    @Autowired
    TeamRepository teamRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public JSONObject search(@RequestParam("type") String type, @RequestParam("delegation") Integer delegationId, @RequestParam("sport") Integer sportId, @RequestParam("name") String name) {
        JSONObject jsonObject = new JSONObject();
        if (type.equals("athlete")) {
            if (delegationId == -1 && sportId == -1 && name.equals(""))
                jsonObject.put("list", individualRepository.findAll());
            else if (delegationId != -1 && sportId == -1 && name.equals(""))
                jsonObject.put("list", individualRepository.findAllByDelegations_Id(delegationId));
            else if (delegationId == -1 && sportId != -1 && name.equals(""))
                jsonObject.put("list", individualRepository.findAllBySportId(sportId));
            else if (delegationId == -1 && sportId == -1 && !name.equals(""))
                jsonObject.put("list", individualRepository.findAllByNameContains(name));
            else if (delegationId != -1 && sportId != -1 && name.equals(""))
                jsonObject.put("list",individualRepository.findAllBySportIdAndDelegationsId(sportId,delegationId));
            else if (delegationId != -1 && sportId == -1 && !name.equals(""))
                jsonObject.put("list", individualRepository.findAllByDelegationsIdAndNameContains(delegationId,name));
            else if (delegationId == -1 && sportId != -1 && !name.equals(""))
                jsonObject.put("list", individualRepository.findAllBySportIdAndNameContains(sportId,name));
            else
                jsonObject.put("list", individualRepository.findAllByDelegationsIdAndNameContainsAndSportID(sportId,name,delegationId));
            return CommonUtil.successJson(jsonObject);
        } else if (type.equals("team")) {
            if (delegationId == -1 && sportId == -1 && name.equals(""))
                jsonObject.put("list", teamRepository.findAll());
            else if (delegationId != -1 && sportId == -1 && name.equals(""))
                jsonObject.put("list", teamRepository.findAllByDelegations_Id(delegationId));
            else if (delegationId == -1 && sportId != -1 && name.equals(""))
                jsonObject.put("list", teamRepository.findAllBySportId(sportId));
            else if (delegationId == -1 && sportId == -1 && !name.equals(""))
                jsonObject.put("list", teamRepository.findAllByNameContains(name));
            else if (delegationId != -1 && sportId != -1 && name.equals(""))
                jsonObject.put("list",teamRepository.findAllBySportIdAndDelegationsId(sportId,delegationId));
            else if (delegationId != -1 && sportId == -1 && !name.equals(""))
                jsonObject.put("list", teamRepository.findAllByDelegationsIdAndNameContains(delegationId,name));
            else if (delegationId == -1 && sportId != -1 && !name.equals(""))
                jsonObject.put("list", teamRepository.findAllBySportIdAndNameContains(sportId,name));
            else
                jsonObject.put("list", teamRepository.findAllByDelegationsIdAndNameContainsAndSportID(sportId,name,delegationId));
            return CommonUtil.successJson(jsonObject);
        } else
            return CommonUtil.errorJson(ErrorEnum.E_503);
    }
}
