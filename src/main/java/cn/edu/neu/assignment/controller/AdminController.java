package cn.edu.neu.assignment.controller;

import cn.edu.neu.assignment.inter.*;
import cn.edu.neu.assignment.model.Delegation;
import cn.edu.neu.assignment.model.Individual;
import cn.edu.neu.assignment.utl.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IndividualRepository individualRepository;
    @GetMapping("individual/delete/{id}")
    public JSONObject individualDelete(@PathVariable(value = "id") Integer id) {
        individualRepository.deleteById(2);
        return CommonUtil.successJson();
    }

}
