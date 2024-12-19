package com.reggie.handler;

import com.reggie.constant.MessageConstant;
import com.reggie.constant.ValidationMessageConstant;
import com.reggie.exception.BaseException;
import com.reggie.exception.ValidationException;
import com.reggie.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 捕获所有的异常
     *
     * @param ex 异常对象
     * @return 异常信息
     */
    @ExceptionHandler
    public R<String> exceptionHandler(BaseException ex) {
        log.info("捕获到异常：{}", ex.getMessage());
        MessageConstant messageConstant = ex.getMessageConstant();
        String message = messageConstant.getMessage();
        Integer code = messageConstant.getCode();
        return R.error(code,message);
    }
    @ExceptionHandler
    public R<String> handleValidationException(ValidationException ex){
        log.info("捕获到参数校验异常:{}",ex.getMessage());
        ValidationMessageConstant validationMessageConstant = ex.getValidationMessageConstant();
        String message = validationMessageConstant.getMessage();
        Integer coed = validationMessageConstant.getCode();
        return R.error(coed,message);

    }

    /**
     * 捕获SQL异常
     * @param ex 异常对象
     * @return 异常信息
     */
    @ExceptionHandler
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.info("捕获到SQL异常：{}", ex.getMessage());
        //Duplicate entry 'xiaohua' for key 'idx_username'
        if (ex.getMessage().contains("Duplicate entry")) {
            //获取重复员工账号 'xiaohua'
            String s = ex.getMessage().split(" ")[2];
            return R.error(s + "已存在");

        }
        return R.error("未知异常");
    }

}
