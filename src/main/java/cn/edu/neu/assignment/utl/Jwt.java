package cn.edu.neu.assignment.utl;


import cn.edu.neu.assignment.utl.constants.Constants;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: CCM 20164969
 * @description: The class to implement a authentication system
 */
public class Jwt {


    /**
     * secret key
     */
    private static final byte[] SECRET = "3d990d2276917dfac04467df11fff26d".getBytes();

    /**
     * {
     * "alg":"HS256",
     * "type":"JWT"
     * }
     */
    private static final JWSHeader header = new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT, null, null, null, null, null, null, null, null, null, null, null);

    /**
     * Create a token
     */
    public static String createToken(Map<String, Object> payload) {
        String tokenString = null;
        JWSObject jwsObject = new JWSObject(header, new Payload(new JSONObject(payload)));
        try {
            // jwsObject do the HMAC sign
            jwsObject.sign(new MACSigner(SECRET));
            tokenString = jwsObject.serialize();
        } catch (JOSEException e) {
            System.err.println("签名失败:" + e.getMessage());
            e.printStackTrace();
        }
        return tokenString;
    }


    /**
     * check token invalid or not
     *
     * @param token
     * @return Map<String   ,       Object>
     */
    public static Map<String, Object> validToken(String token) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            Payload payload = jwsObject.getPayload();
            JWSVerifier verifier = new MACVerifier(SECRET);

            if (jwsObject.verify(verifier)) {
                JSONObject jsonOBj = payload.toJSONObject();
                resultMap.put("state", TokenState.VALID.toString());
                if (jsonOBj.containsKey("expiryTime")) {
                    long extTime = Long.valueOf(jsonOBj.get("expiryTime").toString());
                    long curTime = new Date().getTime();
                    if (curTime > extTime) {
                        resultMap.clear();
                        resultMap.put("state", TokenState.EXPIRED.toString());
                    }
                }
                resultMap.put("data", jsonOBj);

            } else {
                resultMap.put("state", TokenState.INVALID.toString());
            }

        } catch (Exception e) {
            resultMap.clear();
            resultMap.put("state", TokenState.INVALID.toString());
        }
        return resultMap;
    }

    public static String updateToken(HttpServletRequest request) {
        net.minidev.json.JSONObject payload = (net.minidev.json.JSONObject) request.getAttribute("payload");
        Date date = new Date();
        payload.put("startTime", date.getTime());//生成时间
        payload.put("expiryTime", date.getTime() + Constants.EXPIRY_TIME);
        return createToken(payload);
    }

    public static String getUserId(HttpServletRequest request) {
        net.minidev.json.JSONObject payload = (net.minidev.json.JSONObject) request.getAttribute("payload");
        String id = (String) payload.get("userId");
        return id;
    }

    public static String getSessionKey(HttpServletRequest request) {
        net.minidev.json.JSONObject payload = (net.minidev.json.JSONObject) request.getAttribute("payload");
        String sessionKey = (String) payload.get("sessionKey");
        return sessionKey;
    }

}


