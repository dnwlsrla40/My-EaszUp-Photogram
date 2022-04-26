package com.cos.photogramstart.controller.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.controller.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.controller.handler.ex.CustomValidationException;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.dto.CMRespDto;
import com.cos.photogramstart.dto.UserUpdateDto;
import com.cos.photogramstart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable int id, 
                               @Valid UserUpdateDto userUpdateDto, BindingResult bindingResult, // @Vaild 파라미터 바로 뒤에 적어줘야 함
                               @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
        }else{
            User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
            principalDetails.setUser(userEntity);   // 세션 정보 변경 필요
            return new CMRespDto<>(1,"회원수정완료", userEntity);
        }
    }
}
