package cn.edu.neu.assignment.utl.constants;

/**
 * @author: CCM 20164969
 * @description: The class to save constants variable
 */
public class Constants {

    public static final String SUCCESS_CODE = "100";
    public static final String SUCCESS_MSG = "Request success";

    public static final String SESSION_USER_INFO = "userInfo";
    public static final String SESSION_USER_PERMISSION = "userPermission";

    //Token invalid time
    public static final Long EXPIRY_TIME = 1000*60*60l*2;  //2 hours
}
