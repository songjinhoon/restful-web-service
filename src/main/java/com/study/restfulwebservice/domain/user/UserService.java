package com.study.restfulwebservice.domain.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class UserService {

    private static final List<User> users = new ArrayList<>();

    private static final int USERS_COUNT = 3;

    static {
        IntStream.range(0, 3).forEach(value -> {
            users.add(User.builder()
                    .id((long) value)
                    .username("username" + value)
                    .password("password" + value)
                    .name("name" + value)
                    .build());
        });
    }

    public User get(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<User> get() {
        return users;
    }

    public User post(User user) {
        return user;
    }

}
