package com.study.restfulwebservice.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private String name;

}
