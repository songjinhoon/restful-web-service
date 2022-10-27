package com.study.restfulwebservice.domain.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.*;
import lombok.experimental.SuperBuilder;

@JsonFilter("UserInfoV2")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class UserV2 extends User {

    private String grade;

}
