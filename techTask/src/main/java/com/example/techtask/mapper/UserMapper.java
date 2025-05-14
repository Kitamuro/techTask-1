package com.example.techtask.mapper;

import com.example.techtask.entity.User;
import com.example.techtask.request.UserRequest;
import com.example.techtask.response.SubscriptionResponse;
import com.example.techtask.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static User fromRequest(UserRequest dto) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build();
    }

    public static UserResponse toResponse(User user) {
        List<SubscriptionResponse> list = new ArrayList<>();
        user.getSubscriptions().forEach((x) -> list.add(SubscriptionMapper.toResponse(x)));
        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .subscriptions(list)
                .build();
    }
}
