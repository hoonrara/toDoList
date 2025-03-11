package com.example.todo_app.dto;

import com.example.todo_app.domain.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class TodoResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDate dueDate;
    private boolean completed = false;



    public TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.dueDate = todo.getDueDate();
        this.completed = todo.isCompleted();
    }
}
