package com.example.todo_app.service;

import com.example.todo_app.domain.Todo;
import com.example.todo_app.domain.User;
import com.example.todo_app.dto.TodoRequest;
import com.example.todo_app.dto.TodoResponse;
import com.example.todo_app.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    // ✅ 1. 할 일 추가
    public TodoResponse createTodo(TodoRequest request, User user) {
        Todo todo = Todo.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .completed(false)  // 기본적으로 미완료 상태
                .dueDate(request.getDueDate())
                .user(user)
                .build();

        todoRepository.save(todo);
        return new TodoResponse(todo);
    }

    // ✅ 2. 특정 사용자의 할 일 목록 조회
    public List<TodoResponse> getUserTodos(User user) {
        return todoRepository.findByUser(user)
                .stream()
                .map(TodoResponse::new)
                .collect(Collectors.toList());
    }

    // ✅ 3. 할 일 수정
    public TodoResponse updateTodo(Long id, TodoRequest request, User user) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다."));

        if (!todo.getUser().equals(user)) {
            throw new IllegalArgumentException("해당 할 일을 수정할 권한이 없습니다.");
        }

        todo.updateTodo(request.getTitle(), request.getContent(), request.getDueDate());
        return new TodoResponse(todo);
    }

    // ✅ 4. 할 일 완료 처리
    public TodoResponse completeTodo(Long id, User user) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다."));

        if (!todo.getUser().equals(user)) {
            throw new IllegalArgumentException("해당 할 일을 완료할 권한이 없습니다.");
        }

        todo.completeTodo();
        return new TodoResponse(todo);
    }

    // ✅ 5. 할 일 삭제
    public void deleteTodo(Long id, User user) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다."));

        if (!todo.getUser().equals(user)) {
            throw new IllegalArgumentException("해당 할 일을 삭제할 권한이 없습니다.");
        }

        todoRepository.delete(todo);
    }
}
