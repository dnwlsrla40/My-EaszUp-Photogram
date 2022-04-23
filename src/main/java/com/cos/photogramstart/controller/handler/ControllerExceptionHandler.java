package com.cos.photogramstart.controller.handler;

import com.cos.photogramstart.controller.handler.ex.CustomValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice   // Controller 실행 중 Exception 발생 시 다 낚아챔
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)   // RuntimeException과 관련된 모든 Exception 가로챔
    public Map<String,String> validationException(CustomValidationException e) {
        return e.getErrorMap();
    }
}
