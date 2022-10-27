package com.study.restfulwebservice.domain.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@JsonIgnoreProperties(value = {"password", "name"}) 이것도  @JsonIgnore와 동일함
@JsonFilter("UserInfo")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class User {

    private Long id;

    private String username;

    //    @JsonIgnore  걸면 res던질때 json에서 제거됨
    private String password;

    private String name;

}
