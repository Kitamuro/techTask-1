package com.example.techtask.controller;

import com.example.techtask.entity.Subscription;
import com.example.techtask.request.SubscriptionRequest;
import com.example.techtask.request.UserRequest;
import com.example.techtask.response.SubscriptionResponse;
import com.example.techtask.response.UserResponse;
import com.example.techtask.service.SubscriptionService;
import com.example.techtask.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<Subscription> addSubscription(@PathVariable("id") Long id,
                                                        @RequestBody @Valid SubscriptionRequest subscriptionRequest) {
        return subscriptionService.addSubscription(id, subscriptionRequest);
    }

    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<List<SubscriptionResponse>> getSubscriptionsByUserId(@PathVariable("id") Long id) {
        return subscriptionService.getSubscriptionsByUserId(id);
    }

    @DeleteMapping("/{id}/subscriptions/{sub_id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable("id") Long userId, @PathVariable("sub_id") Long subId) {
        return subscriptionService.deleteSubscription(userId, subId);
    }
}
