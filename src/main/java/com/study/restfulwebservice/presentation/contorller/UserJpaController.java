package com.study.restfulwebservice.presentation.contorller;


import com.study.restfulwebservice.domain.User;
import com.study.restfulwebservice.infrastucture.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/jpa/user")
@RestController
public class UserJpaController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> get() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok().body(users);
    }

}
