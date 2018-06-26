package cn.edu.neu.assignment.controller;

import com.alibaba.fastjson.JSONObject;
//import com.neu.demo.inter.UserRepository;
//import com.neu.demo.model.old.User;
//import com.neu.demo.request.Request;
//import CommonUtil;
//import Jwt;
//import Constants;
//import ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@PathVariable() String code){

        return "hello";
//        JSONObject jsonObject = new JSONObject();
//        User user = Request.getUserByCode(code);
//        if (user == null){
//            return CommonUtil.errorJson(ErrorEnum.E_500);
//        }else {
//            Map<String , Object> payload=new HashMap<String, Object>();
//            Date date=new Date();
//            payload.put("userId", user.getId());//用户ID
//            payload.put("sessionKey",user.getSessionKey());
//            payload.put("startTime", date.getTime());//生成时间
//            payload.put("expiryTime",date.getTime()+ Constants.EXPIRY_TIME);//过期时间2小时
//            String token= Jwt.createToken(payload);
//            if(userRepository.existsById(user.getId())){
//                userRepository.saveAndFlush(user);
//                jsonObject.put("isNew",false);
//                return CommonUtil.successJsonWithToken(jsonObject,token);
//            }else {
//                userRepository.save(user);
//                jsonObject.put("isNew",true);
//                return CommonUtil.successJsonWithToken(jsonObject,token);
//            }
//        }
    }

//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public JSONObject login(HttpServletRequest request, @RequestBody JSONObject requestJson){
//        requestJson = requestJson.getJSONObject("user");
//        System.out.println(requestJson.toJSONString());
//        requestJson.put("id",Jwt.getUserId(request));
//        requestJson.put("sessionKey",Jwt.getSessionKey(request));
//        User user = JSONObject.toJavaObject(requestJson,User.class);
//        userRepository.saveAndFlush(user);
//        return CommonUtil.successJson(new JSONObject());
//    }
}