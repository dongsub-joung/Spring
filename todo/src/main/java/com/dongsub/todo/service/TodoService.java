package com.dongsub.todo.service;


import com.dongsub.todo.entitiy.Todo;
import com.dongsub.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Optional<Todo> findTodos(String member_id){
        return todoRepository.findByMemberId(member_id);
    }
}
