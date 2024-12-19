package com.reggie.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 参数校验相关提示常量
 */
@Getter
@AllArgsConstructor
public enum ValidationMessageConstant {

    MISSING_REQUIRED_FIELD(400, "缺少必填字段"),
    INVALID_FIELD_FORMAT(400, "字段格式不正确"),
    FIELD_LENGTH_EXCEEDED(400, "字段长度超出限制"),
    VALUE_OUT_OF_RANGE(400, "字段值超出范围"),
    PARAMETER_VALIDATION_FAILED(400, "参数校验失败"),
    INVALID_PARAMETER(400, "无效的参数");

    private final Integer code;
    private final String message;
}
