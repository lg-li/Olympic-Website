package cn.edu.neu.assignment.controller;


import cn.edu.neu.assignment.inter.DelegationRepository;
import cn.edu.neu.assignment.model.Delegation;
import cn.edu.neu.assignment.utl.CommonUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/delegation")
public class DelegationController {
    @Autowired
    DelegationRepository delegationRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JSONObject list() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", delegationRepository.findAll());
        return CommonUtil.successJson(jsonObject);
    }

    @RequestMapping(value = "/rank",method = RequestMethod.GET)
    public JSONObject rank(){
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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", delegations);
        return CommonUtil.successJson(jsonObject);
    }
}
