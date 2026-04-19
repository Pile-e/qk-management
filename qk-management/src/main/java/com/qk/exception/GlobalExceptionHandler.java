package com.qk.exception;

import com.qk.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestControllerAdvice  // 标识当前是一个异常处理的类
public class GlobalExceptionHandler {


    @ExceptionHandler  // 声明一个方法来处理特定类型的异常。当 Controller 层抛出异常时，Spring 会自动找到匹配的 @ExceptionHandler 方法来处理该异常。
    public Result exceptionHandler(Exception e) {
        log.error("程序报错={}", e.getMessage());
        return Result.error("对不起,服服务器异常务器异常,请稍后重试");
    }

    @ExceptionHandler
    public Result sqlExceptionHandler(DuplicateKeyException e) {
        String message = e.getMessage();
        if (message.contains("Duplicate entry")) {
            String duplicateEntry = message.substring(message.lastIndexOf("Duplicate entry"));
            String[] split = duplicateEntry.split(" ");
            String str = split[2];
            return Result.error("抱歉:" + str + "字段重复,请修改后重试");
        } else {
            return Result.error("对不起,服服务器异常务器异常,请稍后重试");
        }
    }

    @ExceptionHandler
    public Result businessExceptionHandler(BusinessException e) {
        log.error("程序报错={}", e.getMessage());
        return Result.error(e.getMessage());
    }
}
