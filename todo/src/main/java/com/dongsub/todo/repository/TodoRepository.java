package com.dongsub.todo.repository;

import com.dongsub.todo.entitiy.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, String> {
    Optional<Todo> findByMemberId(String member_id);
}
