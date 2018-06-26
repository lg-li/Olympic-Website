package cn.edu.neu.assignment.controller;


import cn.edu.neu.assignment.inter.DelegationRepository;
import cn.edu.neu.assignment.utl.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delegation")
public class DelegationController {
    @Autowired
    DelegationRepository delegationRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JSONObject login() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", delegationRepository.findAll());
        return CommonUtil.successJson(jsonObject);
    }

}
