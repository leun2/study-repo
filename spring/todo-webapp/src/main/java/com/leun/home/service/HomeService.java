package com.leun.home.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  authentication.getName();
    }
}
