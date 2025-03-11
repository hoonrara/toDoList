package com.example.todo_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TodoRequest {
    @NotBlank
    private String title;

    private String content;

    private LocalDate dueDate; // ✅ 마감 기한 필드 추가
}
