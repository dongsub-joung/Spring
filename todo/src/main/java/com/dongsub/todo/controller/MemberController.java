package com.dongsub.todo.controller;

import com.dongsub.todo.dto.ResponseDto;
import com.dongsub.todo.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private boolean loginSuccessful= false;
    @PostMapping(value = "/api/login/{_id}/{_pw}")
    public ResponseDto<?> login(@PathVariable String _id
            , @PathVariable String _pw
            , HttpServletResponse httpServletResponse
            , HttpServletRequest httpServletRequest)
    {
        loginSuccessful= memberService.progressLogin(_id, _pw);

//        Cookie cookie= new Cookie("UserId", _id);
//        cookie.setMaxAge(24 * 30 * 60 * 60 * 1000);
//        httpServletResponse.addCookie(cookie);

        if (loginSuccessful) {
            // If login is successful, create a response with a success message
            loginSuccessful= false;
            return ResponseDto.builder()
                    .success(true)
                    .data("Login successful")
                    .build();
        } else {
            return ResponseDto.fail("ERR", "Login fail");
        }
    }

    @PostMapping(value = "/api/join/{_id}/{_pw}")
    public ResponseDto<?> join(@PathVariable String _id
            , @PathVariable String _pw
            , HttpServletResponse httpServletResponse
            , HttpServletRequest httpServletRequest)
    {
        boolean passing= false;

        String hashedPw= memberService.hashing(_pw);

        passing= memberService.joining(_id, hashedPw);

        if (passing) {
            return ResponseDto.builder()
                    .success(true)
                    .data("Login successful")
                    .build();
        } else {
            return ResponseDto.fail("ERR", "Join fail");
        }
    }
}
