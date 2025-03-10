package com.example.todo_app.dto;

import com.example.todo_app.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String nickname;

    // ✅ User 엔티티를 UserResponse로 변환하는 정적 메서드 추가
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getNickname());
    }
}
