package com.example.todo_app.controller;

import com.example.todo_app.domain.User;
import com.example.todo_app.dto.TodoRequest;
import com.example.todo_app.dto.TodoResponse;
import com.example.todo_app.repository.UserRepository;
import com.example.todo_app.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final UserRepository userRepository; // ✅ User 조회를 위해 추가

    // ✅ 1. 할 일 추가 (POST /api/todos)
    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoRequest request,
                                                   @AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        TodoResponse response = todoService.createTodo(request, user);
        return ResponseEntity.ok(response);
    }

    // ✅ 2. 할 일 목록 조회 (GET /api/todos)
    @GetMapping
    public ResponseEntity<List<TodoResponse>> getTodos(@AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        List<TodoResponse> todos = todoService.getUserTodos(user);
        return ResponseEntity.ok(todos);
    }

    // ✅ 3. 할 일 수정 (PUT /api/todos/{id})
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long id,
                                                   @RequestBody TodoRequest request,
                                                   @AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        TodoResponse response = todoService.updateTodo(id, request, user);
        return ResponseEntity.ok(response);
    }

    // ✅ 4. 할 일 완료 처리 (PATCH /api/todos/{id}/complete)
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoResponse> completeTodo(@PathVariable Long id,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        TodoResponse response = todoService.completeTodo(id, user);
        return ResponseEntity.ok(response);
    }

    // ✅ 5. 할 일 삭제 (DELETE /api/todos/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        todoService.deleteTodo(id, user);
        return ResponseEntity.noContent().build();
    }
}
