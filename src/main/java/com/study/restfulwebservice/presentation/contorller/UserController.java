package com.study.restfulwebservice.presentation.contorller;

import com.study.restfulwebservice.domain.User;
import com.study.restfulwebservice.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.get(id));
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().body(userService.get());
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
