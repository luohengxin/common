package com.cn.common.mvc.advice;

import com.cn.common.mvc.http.HttpCodeEnum;
import com.cn.common.mvc.http.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@ControllerAdvice
public class GlobalExceptionAdvice implements ResponseBodyAdvice {

    private Logger LOG = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return HttpResult.ok(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public HttpResult methodArgumentNotValidException(Exception e) {
        LOG.error(e.getMessage(),e);
        return HttpResult.fail(HttpCodeEnum.METHOD_ARGUMENT_NOT_VALID_EXCEPTION);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public HttpResult exception(Exception e) {
        LOG.error(e.getMessage(),e);
        return HttpResult.fail(HttpCodeEnum.APPLICATION_EXCEPTION);
    }

}
