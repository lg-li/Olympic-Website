package cn.edu.neu.assignment.controller;

import cn.edu.neu.assignment.inter.TypeRepository;
import cn.edu.neu.assignment.utl.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The request controller to respond request for sport type
 */
@RestController
@RequestMapping("/sport")
public class TypeController {
    @Autowired
    TypeRepository typeRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JSONObject list() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", typeRepository.findAll());
        return CommonUtil.successJson(jsonObject);
    }
}
