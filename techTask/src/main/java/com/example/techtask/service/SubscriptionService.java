package com.example.techtask.service;

import com.example.techtask.entity.Subscription;
import com.example.techtask.entity.User;
import com.example.techtask.mapper.SubscriptionMapper;
import com.example.techtask.mapper.UserMapper;
import com.example.techtask.repository.SubscriptionRepository;
import com.example.techtask.repository.UserRepository;
import com.example.techtask.request.SubscriptionRequest;
import com.example.techtask.response.SubscriptionResponse;
import com.example.techtask.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Slf4j
public class SubscriptionService {
    private SubscriptionRepository subscriptionRepository;
    private UserRepository userRepository;


    public ResponseEntity<Subscription> addSubscription(Long id, SubscriptionRequest subscriptionRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found!"));
        Subscription subscription = SubscriptionMapper.fromRequest(subscriptionRequest, user);
        user.getSubscriptions().add(subscription);
        log.info("Добавили подписку: {}", subscription.getServiceName());
        return ResponseEntity.ok(subscriptionRepository.save(subscription));
    }

    public ResponseEntity<List<SubscriptionResponse>> getSubscriptionsByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found!"));
        UserResponse userResponse = UserMapper.toResponse(user);
        log.info("Выводим подписку: {}", userResponse.getSubscriptions());
        return ResponseEntity.ok(userResponse.getSubscriptions());
    }

    public ResponseEntity<Void> deleteSubscription(Long userId, Long subId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found!"));
        Subscription subscription = subscriptionRepository.findById(subId)
                .orElseThrow(() -> new NoSuchElementException("Subscription not found!"));
        user.getSubscriptions().remove(subscription);
        userRepository.save(user);
        log.info("Удалили подписку: {}", subscription.getServiceName());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<SubscriptionResponse>> getTopThree() {
        List<Subscription> topByServiceName = subscriptionRepository.findTopByServiceName(PageRequest.of(0, 3));
        return ResponseEntity.ok(SubscriptionMapper.toListResponse(topByServiceName));
    }
}
