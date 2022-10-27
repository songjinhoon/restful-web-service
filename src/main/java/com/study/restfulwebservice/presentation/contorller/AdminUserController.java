package com.study.restfulwebservice.presentation.contorller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.study.restfulwebservice.domain.User;
import com.study.restfulwebservice.application.UserService;
import com.study.restfulwebservice.domain.UserV2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/admin-user")
@RestController
public class AdminUserController {

    private final UserService userService;

    //    @GetMapping("/v1/{id}")    uri 버전
    //    @GetMapping(value = "/{id}", params = "ver=1")     파라미터 버전
    //    @GetMapping(value = "/{id}", headers = "X-API-VERSION=1")         헤더 버전
    @GetMapping(value = "/{id}", produces = "application/vnd.company.appv1+json")     // media type 버전
    public ResponseEntity<?> getV1(@PathVariable Long id) {
        User user = userService.get(id);

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "username", "name");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo", simpleBeanPropertyFilter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        mappingJacksonValue.setFilters(filterProvider);

        return ResponseEntity.ok().body(mappingJacksonValue);
    }

    //    @GetMapping("/v2/{id}")
    //    @GetMapping(value = "/{id}", params = "ver=2")
    //    @GetMapping(value = "/{id}", headers = "X-API-VERSION=2")
    @GetMapping(value = "/{id}", produces = "application/vnd.company.appv2+json")
    public ResponseEntity<?> getV2(@PathVariable Long id) {
        User user = userService.get(id);
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2);
        userV2.setGrade("VIP");

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "username", "name", "grade");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfoV2", simpleBeanPropertyFilter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userV2);
        mappingJacksonValue.setFilters(filterProvider);

        return ResponseEntity.ok().body(mappingJacksonValue);
    }

    @GetMapping("/v1")
    public ResponseEntity<?> get() {
        List<User> users = userService.get();

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "username", "name");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo", simpleBeanPropertyFilter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
        mappingJacksonValue.setFilters(filterProvider);

        return ResponseEntity.ok().body(mappingJacksonValue);
    }

}
