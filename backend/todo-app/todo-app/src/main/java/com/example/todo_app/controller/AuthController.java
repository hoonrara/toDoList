package com.example.todo_app.controller;

import com.example.todo_app.dto.TokenResponse;
import com.example.todo_app.dto.UserRequest;
import com.example.todo_app.dto.UserResponse;
import com.example.todo_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody UserRequest request) {
        UserResponse userResponse = userService.signup(request);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserRequest request) {
        String token = userService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new TokenResponse(token));  // ✅ JSON 형태로 반환
    }

}
