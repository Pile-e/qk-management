package com.qk.exception;

import com.qk.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result ex(Exception e) {
        log.error("服务器出现异常", e);
        return Result.error("服务器异常");
    }

    //处理登录业务异常
    @ExceptionHandler
    public Result ex(BusinessException e) {
        log.error("业务异常", e);
        return Result.error(e.getMessage());
    }
}
