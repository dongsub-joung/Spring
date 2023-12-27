package com.dongsub.todo.entitiy;

import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    private String id;

    @Column
    private String member_id;

    @Column
    private String todo_body;

}
