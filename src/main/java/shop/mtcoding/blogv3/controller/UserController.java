package shop.mtcoding.blogv3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv3.dto.user.UserReqDto.JoinReqDto;

@Controller
public class UserController {

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @PostMapping("/join")
    public String join(JoinReqDto joinReqDto) {

        return "redirect:/loginForm";
    }
}
