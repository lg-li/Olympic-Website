//package com.neu.demo.request;
//
//import com.alibaba.fastjson.JSONObject;
//import com.neu.demo.model.old.User;
//
//import java.io.IOException;
//
//public class Request {
//    private static String access = "";
//    private static String appid = "wxf4806e54f322ec01";
//    private static String secret = "b17ce062f9d116dd4577b887daed9576";
//
//    public static User getUserByCode(String code) {
//        try {
//            JSONObject obj = JSONObject.parseObject(Client.sendGetData("https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code", "utf-8"));
//            System.out.println(obj.toJSONString());
//            if (obj.containsKey("errcode")) {
//                return null;
//            } else
//                return new User(obj.getString("openid"), obj.getString("session_key"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
