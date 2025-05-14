package com.example.techtask.mapper;

import com.example.techtask.entity.Subscription;
import com.example.techtask.entity.User;
import com.example.techtask.request.SubscriptionRequest;
import com.example.techtask.response.SubscriptionResponse;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionMapper {
    public static Subscription fromRequest(SubscriptionRequest dto, User user) {
        return Subscription.builder()
                .serviceName(dto.getServiceName())
                .startDate(dto.getStartDate())
                .user(user)
                .build();
    }

    public static SubscriptionResponse toResponse(Subscription subscription) {
        return SubscriptionResponse.builder()
                .serviceName(subscription.getServiceName())
                .startDate(subscription.getStartDate())
                .build();
    }

    public static List<SubscriptionResponse> toListResponse(List<Subscription> subscriptionList) {
        ArrayList<SubscriptionResponse> responses = new ArrayList<>();
        for (Subscription subscription : subscriptionList) {
            responses.add(toResponse(subscription));
        }
        return responses;
    }
}