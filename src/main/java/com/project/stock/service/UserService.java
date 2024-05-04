package com.project.stock.service;

import com.project.stock.dto.user.UserRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public interface UserService {

    UserRequest create(UserRequest userRequest);

    UserRequest update(UserRequest request);

    UserRequest fetch(String email);
}