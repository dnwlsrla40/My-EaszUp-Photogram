package com.cos.photogramstart.controller.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.dto.CMRespDto;
import com.cos.photogramstart.dto.UserUpdateDto;
import com.cos.photogramstart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable int id, UserUpdateDto userUpdateDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
        principalDetails.setUser(userEntity);   // 세션 정보 변경 필요
        return new CMRespDto<>(1,"회원수정완료", userEntity);
    }
}
