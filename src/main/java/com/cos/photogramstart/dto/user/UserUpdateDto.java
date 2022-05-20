package com.cos.photogramstart.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    private String website;
    private String bio;
    private String phone;
    private String gender;


    // 필수 아닌 것들이 있어 조금 위험. 코드 수정 예정
    public User toEntity() {
        return User.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }

}
