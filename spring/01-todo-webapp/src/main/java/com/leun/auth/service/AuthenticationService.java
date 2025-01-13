package com.leun.auth.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String username, String password){

        boolean isValidUserName = username.equalsIgnoreCase("lee");
        boolean isValidPassword = password.equalsIgnoreCase("1234");

        return isValidPassword && isValidUserName;
    }
}
