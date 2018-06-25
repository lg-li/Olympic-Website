package cn.edu.neu.assignment.utl.constants;

public enum ErrorEnum {
    /*
    * 错误信息
    * */
    E_400("400", "请求处理异常，请稍后再试"),
    E_500("500", "用户名或密码错误"),
    E_501("501", "请求路径不存在"),
    E_502("502", "邮箱已被注册"),
    E_503("503", "邮箱或用户名已被使用"),
    E_504("503", "邮箱不存在"),
    E_505("503", "密码错误"),

    E_90003("90003", "缺少必填参数");

    private String errorCode;

    private String errorMsg;

    ErrorEnum() {
    }

    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
