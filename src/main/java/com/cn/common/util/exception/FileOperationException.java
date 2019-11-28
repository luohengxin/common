package com.cn.common.util.exception;

public class FileOperationException extends RuntimeException {
    public FileOperationException() {
        super();
    }

    public FileOperationException(String message) {
        super(message);
    }

    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileOperationException(Throwable cause) {
        super(cause);
    }

    protected FileOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
