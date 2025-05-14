package com.example.techtask.repository;

import com.example.techtask.entity.Subscription;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("SELECT s.serviceName, COUNT(s) as total " +
            "FROM Subscription s " +
            "GROUP BY s.serviceName " +
            "ORDER BY total DESC")
    List<Subscription> findTopByServiceName(Pageable pageable);
}
