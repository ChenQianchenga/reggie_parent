package com.reggie.exception;

import com.reggie.constant.MessageConstant;

/**
 * 账号被锁定异常
 */
public class AccountLockedException extends BaseException {

    private static final long serialVersionUID = 1L;  // 保证序列化的一致性

    public AccountLockedException() {
        super(MessageConstant.ACCOUNT_LOCKED);  // 使用 MessageConstant 来获取错误信息
    }

    // 带有 MessageConstant 和 Throwable 原因的构造方法
    public AccountLockedException(MessageConstant messageConstant, Throwable cause) {
        super(messageConstant, cause);
    }

    // 带有异常信息和 Throwable 原因的构造方法
    public AccountLockedException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
