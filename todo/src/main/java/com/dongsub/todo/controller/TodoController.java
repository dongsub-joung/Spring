package com.dongsub.todo.controller;

import com.dongsub.todo.dto.ResponseDto;
import com.dongsub.todo.entitiy.Todo;
import com.dongsub.todo.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class TodoController{

    @Autowired
    private final TodoService todoService;

    @PostMapping(value = "/api/todo/add/{userId}/{textBody}")
    public ResponseDto<?> todoAdd(
            HttpServletResponse httpServletResponse
            , HttpServletRequest httpServletRequest
            , @PathVariable String userId
            , @PathVariable String textBody)
    {
        boolean passing= false;
        passing= todoService.saveTodoData(userId, textBody);
        if (passing) {
            // If login is successful, create a response with a success message
            return ResponseDto.builder()
                    .success(true)
                    .data("Todo Add successful")
                    .build();
        } else {
            return ResponseDto.fail("ERR", "Todo Add fail");
        }
    }

    @PostMapping(value = "/api/todos/{userId}")
    public ResponseDto<?> todoAdd(HttpServletResponse httpServletResponse
            , HttpServletRequest httpServletRequest
            ,@PathVariable String userId)
    {
        boolean passing= false;
        ArrayList<Todo> todos= new ArrayList<>();
        try {
            todos= todoService.findTodos(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        passing=true;

        if (passing) {
            // If login is successful, create a response with a success message
            return ResponseDto.builder()
                    .success(true)
                    .data(todos)
                    .build();
        } else {
            return ResponseDto.fail("ERR", "todo load fail");
        }
    }
}
