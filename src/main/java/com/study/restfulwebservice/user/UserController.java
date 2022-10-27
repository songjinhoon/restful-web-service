package com.study.restfulwebservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> get() {
        List<User> users = new ArrayList<>();
        IntStream.range(0, 5).forEach(value -> {
            users.add(User.builder()
                    .id((long) value)
                    .username("username" + value)
                    .password("password" + value)
                    .name("name" + value)
                    .build());
        });
        return ResponseEntity.ok().body(users);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody User user) {
//        return ResponseEntity.created(URI.create("/api/user/" + user.getId())).build();
        User saveUser = userService.post(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();
        return ResponseEntity.created(uri).build();

    }

}
