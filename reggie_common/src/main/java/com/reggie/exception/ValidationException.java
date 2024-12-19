package com.reggie.exception;

import com.reggie.constant.ValidationMessageConstant;
import lombok.Data;

@Data
public class ValidationException extends RuntimeException {
    private ValidationMessageConstant validationMessageConstant;

    public ValidationException(){

    }


    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(ValidationMessageConstant validationMessageConstant){
        super(validationMessageConstant.getMessage());
        this.validationMessageConstant = validationMessageConstant;
    }
}
