package com.cn.common.util.exception;

public class BeanConvertException extends RuntimeException {
    public BeanConvertException() {
        super();
    }

    public BeanConvertException(String message) {
        super(message);
    }

    public BeanConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanConvertException(Throwable cause) {
        super(cause);
    }

    protected BeanConvertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
