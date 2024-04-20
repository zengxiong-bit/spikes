package com.whkj.spikes.bo.rest;

import java.io.Serializable;

public class RestPageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private String requestId;

    private PageResult<T> data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getRequestId() {
        return requestId;
    }

    public PageResult<T> getData() {
        return data;
    }

    /**
     * 链式方法
     */
    public RestPageResult<T> data(PageResult<T> data) {
        this.data = data;
        return this;
    }

    public RestPageResult<T> code(int code) {
        this.code = code;
        return this;
    }

    public RestPageResult<T> requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public RestPageResult<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public static RestPageResult success() {
        RestPageResult dto = new RestPageResult();
        dto.code(RestConsts.DEFAULT_SUCCESS_CODE).msg(RestConsts.SUCCESS_MESSAGE);
        return dto;
    }

    public static RestPageResult failure() {
        RestPageResult dto = new RestPageResult();
        dto.msg(RestConsts.ERROR_MESSAGE);
        return dto;
    }

    public RestPageResult() {

    }

}