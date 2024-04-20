package com.whkj.spikes.consts;


public enum ErrorCodeEnum {

    ERROR_BAD_REQUEST(400001, "bad request: %s"),
    ERROR_BAD_PARAM(400002, "NotReadableJsonParameter"),
    ERROR_BAD_REQ_MESSAGE(400003, "%s"),
    ERROR_PARAM(400100, "param error: %s"),

    ERROR_DEFAULT(500000, "%s"),
    ERROR_INTERNAL(500001, "系统繁忙,稍后重试"),
    ERROR_TIMEOUT(500002, "invoke timeout"),
    ERROR_MQ_FAIL(500003, "mq handle fail"),
    ERROR_SQL(500004, "sql handle error: %s"),
    ERROR_NOT_LOGIN(500005, "未获取到用户信息"),

    /*
     * zk锁失败
     */
    ERROR_ZK_LOCK_FAILED(500006, "%s"),


    /**
     * 无权限访问
     */
    ERROR_NO_PERMISSON(500400, "您无权限操作")
    ;

    private final Integer errorCode;

    private final String errorMsg;

    ErrorCodeEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
