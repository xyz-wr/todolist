package com.todolist.todoapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long todoId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private long todoOrder;

    @Column(nullable = false)
    private boolean completed;

}
