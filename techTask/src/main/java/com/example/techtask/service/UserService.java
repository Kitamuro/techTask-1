package com.example.techtask.service;

import com.example.techtask.entity.User;
import com.example.techtask.mapper.UserMapper;
import com.example.techtask.repository.UserRepository;
import com.example.techtask.request.UserRequest;
import com.example.techtask.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private UserRepository userRepository;

    public ResponseEntity<Void> create(UserRequest userRequest) {
        log.info("Создание пользователя: {}", userRequest.getEmail());
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует");
        }
        User user = UserMapper.fromRequest(userRequest);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<UserResponse> getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("There is no user by this ID"));
        log.info("Выводим пользователя: {}", user.getEmail());
        return ResponseEntity.ok(UserMapper.toResponse(user));
    }

    public ResponseEntity<Void> updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("There is no user by this ID"));
        user.setEmail(userRequest.getEmail() == null ? user.getEmail() : userRequest.getEmail());
        user.setUsername(userRequest.getUsername() == null ? user.getUsername() : userRequest.getUsername());
        userRepository.save(user);
        log.info("Обновили пользователя: {}", user.getEmail());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteUser(Long id) {
        log.info("Удалили пользователя: {}", id);
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
