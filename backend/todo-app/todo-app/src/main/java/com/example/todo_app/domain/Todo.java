package com.example.todo_app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "todos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String content;

    @Column(nullable = false)
    private boolean completed = false;

    @Column(nullable = true)
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // 할 일을 작성한 사용자

    @Builder
    public Todo(String title, String content, boolean completed, LocalDate dueDate, User user) {
        this.title = title;
        this.content = content;
        this.completed = completed;
        this.dueDate = dueDate;
        this.user = user;
    }

    // ✅ 할 일 완료 체크 기능
    public void completeTodo() {
        this.completed = true;
    }

    // ✅ 할 일 수정 기능
    public void updateTodo(String title, String content, LocalDate dueDate) {
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
    }
}
