package com.dongsub.todo.service;


import com.dongsub.todo.entitiy.Todo;
import com.dongsub.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public ArrayList<Todo> findTodos(String member_id){
        return todoRepository.findAllByMemberId(member_id).orElseThrow(
                () -> new NullPointerException("null")
        );
    }

    public Long getIndex() {
        var optionalTodo= todoRepository.findFirstByOrderByIdDesc();
        Todo todo= optionalTodo.orElseThrow( () -> new NullPointerException());

        return todo.getId();
    }

    public boolean saveTodoData(String userId, String textBody) {
        Todo todo= new Todo(userId, textBody);

        try {
            todoRepository.save(todo);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
