package com.cn.common.mvc.http;


public class HttpResult<T> {


    private static final long serialVersionUID = -8971855226086747460L;

    private boolean success;

    private T data;

    private int status;

    private String message;

    private HttpResult(boolean success, T data, int status, String message) {
        this.success = success;
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public static <T> HttpResult<T> ok(T data){
        return new HttpResult<>(Boolean.TRUE,data,HttpCodeEnum.SUCCESS.getStatus(), HttpCodeEnum.SUCCESS.getMessage());
    }

    public static <T> HttpResult<T> fail(HttpCodeEnum httpCodeEnum){
        return new HttpResult<>(Boolean.FALSE,null,httpCodeEnum.getStatus(), httpCodeEnum.getMessage());
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
