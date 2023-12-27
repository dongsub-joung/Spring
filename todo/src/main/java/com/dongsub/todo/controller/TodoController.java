package com.dongsub.todo.controller;

import com.dongsub.todo.dto.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoController {
    @PostMapping(value = "/api/todo/add")
    public ResponseDto<?> todoAdd(@RequestParam String _id
            , @RequestParam String _pw
            , HttpServletResponse httpServletResponse
            , HttpServletRequest httpServletRequest)
    {
        boolean passing= false;
        

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

    @PostMapping(value = "/api/todos")
    public ResponseDto<?> todoAdd(HttpServletResponse httpServletResponse
            , HttpServletRequest httpServletRequest)
    {

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
