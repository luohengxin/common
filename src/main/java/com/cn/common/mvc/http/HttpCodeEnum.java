package com.cn.common.mvc.http;

public enum HttpCodeEnum {

    SUCCESS(200, "success"),
    PAGE_PARAM_EXCEPTION(960001,"pageNo or pageSize  is not legal and must be a positive integer。"),
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION(960002, "method parameter validation failed"),
    APPLICATION_EXCEPTION(960000, "application exception")

    ;

    private int status;

    private String message;

    HttpCodeEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
