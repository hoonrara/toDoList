package com.example.todo_app.controller;

import com.example.todo_app.dto.UserRequest;
import com.example.todo_app.dto.UserResponse;
import com.example.todo_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ✅ 현재 로그인한 유저 정보 조회 (GET /api/users/me)
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        UserResponse response = userService.getUserByEmail(userEmail);
        return ResponseEntity.ok(response);
    }

    // ✅ 유저 닉네임 수정 (PUT /api/users/me)
    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateNickname(@AuthenticationPrincipal UserDetails userDetails,
                                                       @RequestBody UserRequest request) {
        String userEmail = userDetails.getUsername();
        UserResponse response = userService.updateNickname(userEmail, request.getNickname());
        return ResponseEntity.ok(response);
    }
}
