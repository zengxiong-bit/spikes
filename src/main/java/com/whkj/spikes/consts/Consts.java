package com.whkj.spikes.consts;

public class Consts {

    private Consts(){}

    public static final int NCPU = Runtime.getRuntime().availableProcessors();

    public static final Integer DEFAULT_ERROR_CODE = 500;

    public static final Integer DEFAULT_HTTP_SUCCESS_RESP_CODE = 200;

    public static final String INTERNAL_ERROR_DEFAULT_SHOW_MSG = "系统繁忙,稍后重试";

}
