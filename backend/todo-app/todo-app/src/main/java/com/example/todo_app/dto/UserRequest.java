package com.example.todo_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private String nickname; // ✅ 닉네임 필드 추가
}
