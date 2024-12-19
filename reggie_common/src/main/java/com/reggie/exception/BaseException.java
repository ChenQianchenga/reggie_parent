package com.reggie.exception;

import com.reggie.constant.MessageConstant;
import lombok.Data;

/**
 * 业务异常
 */
@Data
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;  // 保证序列化的一致性

    private MessageConstant messageConstant;

    // 无参构造函数
    public BaseException() {
        super();
    }

    // 带有异常信息的构造函数
    public BaseException(String msg) {
        super(msg);
    }

    // 带有 MessageConstant 的构造函数
    public BaseException(MessageConstant messageConstant) {
        super(messageConstant != null ? messageConstant.getMessage() : null);  // 避免 messageConstant 为 null
        this.messageConstant = messageConstant;
    }

    // 带有 MessageConstant 和 Throwable 的构造函数
    public BaseException(MessageConstant messageConstant, Throwable cause) {
        super(messageConstant != null ? messageConstant.getMessage() : null, cause);
        this.messageConstant = messageConstant;
    }

    // 带有异常信息和 Throwable 的构造函数
    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
