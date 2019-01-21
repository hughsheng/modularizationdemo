package com.tl.commonsdk.api.bean;

/**
 * Created by linfp on 2016/7/27.
 * 所有请求结果的bean
 */
public class BaseResultBean {
    private int code;

    private String msg;

    public int getResult() {
        return code;
    }

    public void setResult(int result) {
        this.code = result;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }
}
