package com.example.todo_app.repository;

import com.example.todo_app.domain.Todo;
import com.example.todo_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);  // 특정 사용자의 할 일 목록 조회
}
