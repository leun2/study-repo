package com.leun.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String userName;

    private String userPassword;

}
