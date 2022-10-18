package com.study.restfulwebservice.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User post(User user) {
        return user;
    }

}
