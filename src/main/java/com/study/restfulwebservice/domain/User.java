package com.study.restfulwebservice.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@JsonIgnoreProperties(value = {"password", "name"}) 이것도  @JsonIgnore와 동일함
//@JsonFilter("UserInfo")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    //    @JsonIgnore  걸면 res던질때 json에서 제거됨
    private String password;

    private String name;

}
