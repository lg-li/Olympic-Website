package cn.edu.neu.assignment.utl.constants;

/**
 * @author: CCM 20164969
 * @description: The class to save error type
 */
public enum ErrorEnum {
    /*
    * error message
    * */
    E_400("400", "Request error,please try again later!"),
    E_501("501", "Undefined path!"),
    E_502("502", "Don't exist!");


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
