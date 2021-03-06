package com.cos.photogramstart.controller;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.dto.user.UserProfileDto;
import com.cos.photogramstart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
        model.addAttribute("dto", dto);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
//            , Model model) {  // security tag libs를 pom.xml에 사용하여 jsp에 세션 정보를 넘겨줄 필요가 없다. (자세한 건 header.jsp 참조)
//        model.addAttribute("principal", principalDetails.getUser());


        // SecurityContextHolder.getContext().getAuthentication().getPrincipal().getUser();
        // 해줘야 할 것을 안하고 세션 정보를 가져올 수 있도록 Security에서 @AuthenticationPrincipal 제공
        return "user/update";
    }
}
