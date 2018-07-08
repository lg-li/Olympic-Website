package cn.edu.neu.assignment.controller;

import cn.edu.neu.assignment.utl.CommonUtil;
import cn.edu.neu.assignment.utl.Jwt;
import cn.edu.neu.assignment.utl.constants.Constants;
import cn.edu.neu.assignment.utl.constants.ErrorEnum;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * The request controller to respond request of user information.
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@RequestBody JSONObject requestJson) {
        String username = (String) requestJson.get("username");
        String password = (String) requestJson.get("password");

        //check username and password
        if (!username.equals("jsp") || !password.equals("123456"))
            return CommonUtil.errorJson(ErrorEnum.E_500);
        else {
            JSONObject jsonObject = new JSONObject();
            Map<String, Object> payload = new HashMap<String, Object>();
            Date date = new Date();
            //add payload to create a token
            payload.put("userId", username);
            payload.put("startTime", date.getTime());
            payload.put("expiryTime", date.getTime() + Constants.EXPIRY_TIME);
            String token = Jwt.createToken(payload);
            return CommonUtil.successJsonWithToken(jsonObject, token);
        }
    }
}