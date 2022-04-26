package com.cos.photogramstart.controller.api;

import com.cos.photogramstart.dto.UserUpdateDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PutMapping("/api/user/{id}")
    public String update(UserUpdateDto userUpdateDto) {
        return "ok";
    }
}
