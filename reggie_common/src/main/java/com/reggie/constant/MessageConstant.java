package com.reggie.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 信息提示常量类
 */
@Getter
@AllArgsConstructor
public enum MessageConstant {

    // 通用错误
    PASSWORD_ERROR(400, "密码错误"),
    ACCOUNT_NOT_FOUND(404, "账号不存在"),
    ACCOUNT_LOCKED(403, "账号被锁定"),
    UNKNOWN_ERROR(500, "未知错误"),
    USER_NOT_LOGIN(401, "用户未登录"),
    LOGIN_FAILED(400, "登录失败"),
    PASSWORD_EDIT_FAILED(400, "密码修改失败"),

    // 分类相关
    CATEGORY_BE_RELATED_BY_SETMEAL(400, "当前分类关联了套餐，不能删除"),
    CATEGORY_BE_RELATED_BY_DISH(400, "当前分类关联了菜品，不能删除"),

    // 购物车与地址簿相关
    SHOPPING_CART_IS_NULL(400, "购物车数据为空，不能下单"),
    ADDRESS_BOOK_IS_NULL(400, "用户地址为空，不能下单"),

    // 文件相关
    UPLOAD_FAILED(500, "文件上传失败"),

    // 套餐与菜品相关
    SETMEAL_ENABLE_FAILED(400, "套餐内包含未启售菜品，无法启售"),
    DISH_ON_SALE(400, "起售中的菜品不能删除"),
    SETMEAL_ON_SALE(400, "起售中的套餐不能删除"),
    DISH_BE_RELATED_BY_SETMEAL(400, "当前菜品关联了套餐，不能删除"),

    // 订单相关
    ORDER_STATUS_ERROR(400, "订单状态错误"),
    ORDER_NOT_FOUND(404, "订单不存在");

    /**
     * 错误代码，参考 HTTP 状态码
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private final String message;

    /**
     * 单参数构造器，默认错误代码为 400
     */
    MessageConstant(String message) {
        this(400, message);
    }
}
