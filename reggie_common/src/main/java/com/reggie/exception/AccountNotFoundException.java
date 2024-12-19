package com.reggie.exception;

import com.reggie.constant.MessageConstant;

/**
 * 账号不存在异常
 */
public class AccountNotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;  // 保证序列化的一致性

    // 无参构造方法，默认使用 MessageConstant.ACCOUNT_NOT_FOUND
    public AccountNotFoundException() {
        super(MessageConstant.ACCOUNT_NOT_FOUND);
    }

    // 带有错误信息的构造方法
    public AccountNotFoundException(String msg) {
        super(msg);
    }

    // 带有 MessageConstant 和 Throwable 原因的构造方法
    public AccountNotFoundException(MessageConstant messageConstant, Throwable cause) {
        super(messageConstant, cause);
    }

    // 带有异常信息和 Throwable 原因的构造方法
    public AccountNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
