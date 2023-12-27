package com.dongsub.todo.controller;

import com.dongsub.todo.dto.ResponseDto;
import com.dongsub.todo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private boolean loginSuccessful= false;
    @PostMapping(value = "/api/login")
    public ResponseDto<?> login(@RequestParam String _id
            , @RequestParam String _pw
            , HttpServletResponse httpServletResponse
            , HttpServletRequest httpServletRequest)
    {
        loginSuccessful= memberService.progressLogin(_id, _pw);

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

    @PostMapping(value = "/api/join")
    public ResponseDto<?> join(@RequestParam String _id
            , @RequestParam String _pw
            , HttpServletResponse httpServletResponse
            , HttpServletRequest httpServletRequest)
    {
        boolean passing= false;

        String hashedPw= memberService.hashing(_pw);

        passing= memberService.joining(_id, hashedPw);

        if (passing) {
            // If login is successful, create a response with a success message
            return ResponseDto.builder()
                    .success(true)
                    .data("Login successful")
                    .build();
        } else {
            return ResponseDto.fail("ERR", "Join fail");
        }
    }
}
