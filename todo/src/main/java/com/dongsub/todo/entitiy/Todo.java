package com.dongsub.todo.entitiy;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberId;

    @Column
    private String todoBody;

    public Todo(String memberId, String txt){
        this.memberId= memberId;
        this.todoBody= txt;
    }
}
