package com.reggie.exception;

import com.reggie.constant.MessageConstant;

/**
 * 密码错误异常
 */
public class PasswordErrorException extends BaseException {

    private static final long serialVersionUID = 1L;  // 保证序列化的一致性

    // 无参构造方法，默认使用 MessageConstant.PASSWORD_ERROR
    public PasswordErrorException() {
        super(MessageConstant.PASSWORD_ERROR);
    }

    // 带有错误信息的构造方法
    public PasswordErrorException(String msg) {
        super(msg);
    }

    // 带有 MessageConstant 和 Throwable 原因的构造方法
    public PasswordErrorException(MessageConstant messageConstant, Throwable cause) {
        super(messageConstant, cause);
    }

    // 带有异常信息和 Throwable 原因的构造方法
    public PasswordErrorException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
