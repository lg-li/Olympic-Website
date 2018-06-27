package cn.edu.neu.assignment.utl;


import cn.edu.neu.assignment.utl.constants.ErrorEnum;
import com.alibaba.fastjson.JSONObject;
import cn.edu.neu.assignment.utl.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;


/**
 * @author: CCM 20164969
 * @description: The class to package the return msg
 */
public class CommonUtil {

    /**
     * Success with null return
     *
     * @return
     */
    public static JSONObject successJson() {
        return successJson(new JSONObject());
    }

    /**
     * Success with 100 code and a return data
     */
    public static JSONObject successJson(Object returnData) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", Constants.SUCCESS_CODE);
        resultJson.put("msg", Constants.SUCCESS_MSG);
        resultJson.put("data", returnData);
        return resultJson;
    }

    /**
     *  Success with 100 code , a new token and a return data
     */
    public static JSONObject successJsonWithToken(Object returnData, String token) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", Constants.SUCCESS_CODE);
        resultJson.put("msg", Constants.SUCCESS_MSG);
        resultJson.put("data", returnData);
        resultJson.put("token", token);
        return resultJson;
    }

    /**
     * Fail with a error
     */
    public static JSONObject errorJson(ErrorEnum errorEnum) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", errorEnum.getErrorCode());
        resultJson.put("msg", errorEnum.getErrorMsg());
        resultJson.put("data", new JSONObject());
        return resultJson;
    }

    public static JSONObject errorJson(ErrorEnum errorEnum, Object object) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", errorEnum.getErrorCode());
        resultJson.put("msg", errorEnum.getErrorMsg());
        resultJson.put("data", object);
        return resultJson;
    }

    /**
     * @param request
     * @return
     */
    public static JSONObject request2Json(HttpServletRequest request) {
        JSONObject requestJson = new JSONObject();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] pv = request.getParameterValues(paramName);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pv.length; i++) {
                if (pv[i].length() > 0) {
                    if (i > 0) {
                        sb.append(",");
                    }
                    sb.append(pv[i]);
                }
            }
            requestJson.put(paramName, sb.toString());
        }
        return requestJson;
    }
}
