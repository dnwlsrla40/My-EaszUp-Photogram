package com.cos.photogramstart.controller;

import com.cos.photogramstart.dto.auth.SignupDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller // 1.IOC등록 2.File 리턴하는 컨트롤러
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto) { // key=value (x-www-form-urlencoded) 기본적으로 Spring Controller가 받는 형식
        logger.info(signupDto.toString());
        return "auth/signin";
    }

}
