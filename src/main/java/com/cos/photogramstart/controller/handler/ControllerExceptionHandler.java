package com.cos.photogramstart.controller.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice   // Controller 실행 중 Exception 발생 시 다 낚아챔
public class ControllerExceptionHandler {

    @ExceptionHandler(RuntimeException.class)   // RuntimeException과 관련된 모든 Exception 가로챔
    public String validationException(RuntimeException e) {
        return e.getMessage();
    }
}
