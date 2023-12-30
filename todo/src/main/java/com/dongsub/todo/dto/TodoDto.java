package com.dongsub.todo.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TodoDto {
    private String memberId;
    private String todoBody;

    public TodoDto(String memberId, String txt){
        this.memberId= memberId;
        this.todoBody= txt;
    }
}
