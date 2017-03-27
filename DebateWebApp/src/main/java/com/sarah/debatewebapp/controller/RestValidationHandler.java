/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.controller;

import com.sarah.debatewebapp.validation.ValidationErrorContainer;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * @author Sarah
 */

@ControllerAdvice
public class RestValidationHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorContainer processValidationErrors(MethodArgumentNotValidException e){         
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        
        ValidationErrorContainer errors = new ValidationErrorContainer();
        for(FieldError fError : fieldErrors){
            errors.addValidationError(fError.getField(), fError.getDefaultMessage());
        }
        
        return errors;
    }

}
