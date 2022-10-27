package com.study.restfulwebservice.presentation.contorller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.study.restfulwebservice.application.UserService;
import com.study.restfulwebservice.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        User user = userService.get(id);

        // hateoas
        EntityModel<User> entityModel = EntityModel.of(user);
        entityModel.add(linkTo(methodOn(this.getClass()).get(id)).withSelfRel());
        entityModel.add(linkTo(methodOn(this.getClass()).get()).withRel("all-user"));

        return ResponseEntity.ok().body(entityModel);
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
