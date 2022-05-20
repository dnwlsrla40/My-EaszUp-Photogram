package com.cos.photogramstart.service;

import com.cos.photogramstart.controller.handler.ex.CustomException;
import com.cos.photogramstart.controller.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserProfileDto 회원프로필(int pageUserId, int principalId) {
        UserProfileDto dto = new UserProfileDto();

        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
        });

        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId == principalId);
        dto.setImageCount(userEntity.getImages().size());

        return dto;
    }

    @Transactional
    public User 회원수정(int id, User user) {

        // 1. 영속화
        User userEntity = userRepository.findById(id).orElseThrow(() -> new CustomValidationApiException("찾을 수 없는 id입니다."));// 1. 무조건 찾았다. 걱정마 get() 2. 못찾았어 익섹션 발동시킬께 or ElseThrow()

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        // 2. 영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료)
        userEntity.setName(user.getName());
        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        return userEntity;
    }
}
