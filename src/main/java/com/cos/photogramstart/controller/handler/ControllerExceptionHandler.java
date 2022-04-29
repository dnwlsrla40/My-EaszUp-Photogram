package com.cos.photogramstart.controller.handler;

import com.cos.photogramstart.controller.handler.ex.CustomApiException;
import com.cos.photogramstart.controller.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.controller.handler.ex.CustomValidationException;
import com.cos.photogramstart.dto.CMRespDto;
import com.cos.photogramstart.util.Script;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice   // Controller 실행 중 Exception 발생 시 다 낚아챔
public class ControllerExceptionHandler {

    // CMRespDto Script 비교
    // 1. 클라이언트 응답 시 - Script 좋음
    // 2. Ajax통신- CMRespDto 방식이 좋음
    // 3. Android 통신 - CMRespDto 방식이 좋음
    
    // 후에 사용(data를 전송)
//    @ExceptionHandler(CustomValidationException.class)   // RuntimeException과 관련된 모든 Exception 가로챔
//    public CMRespDto<?> validationException(CustomValidationException e) {
//        return new CMRespDto<>(-1, e.getMessage(), e.getErrorMap());
//    }

    // Script 전송
    @ExceptionHandler(CustomValidationException.class)   // RuntimeException과 관련된 모든 Exception 가로챔
    public String validationException(CustomValidationException e) {
        return Script.back(e.getErrorMap().toString());
    }

    // Script 전송
    @ExceptionHandler(CustomValidationApiException.class)   // RuntimeException과 관련된 모든 Exception 가로챔
    public ResponseEntity<CMRespDto<?>> validationApiException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    // Script 전송
    @ExceptionHandler(CustomApiException.class)   // RuntimeException과 관련된 모든 Exception 가로챔
    public ResponseEntity<CMRespDto<?>> apiException(CustomApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
}
