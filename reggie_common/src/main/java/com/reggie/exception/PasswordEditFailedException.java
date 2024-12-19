package com.reggie.exception;

import com.reggie.constant.MessageConstant;

/**
 * 密码修改失败异常
 */
public class PasswordEditFailedException extends BaseException{

    public PasswordEditFailedException(){
        super(MessageConstant.PASSWORD_EDIT_FAILED);
    }

    public PasswordEditFailedException(String msg){
        super(msg);
    }

}
