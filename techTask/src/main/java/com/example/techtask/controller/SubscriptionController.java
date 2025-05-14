package com.example.techtask.controller;

import com.example.techtask.response.SubscriptionResponse;
import com.example.techtask.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@AllArgsConstructor
public class SubscriptionController {
    private SubscriptionService subscriptionService;

    @GetMapping("/top")
    private ResponseEntity<List<SubscriptionResponse>> getTopSubscription(){
        return subscriptionService.getTopThree();
    }
}
