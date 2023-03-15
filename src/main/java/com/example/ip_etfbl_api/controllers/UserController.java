package com.example.ip_etfbl_api.controllers;

import com.example.ip_etfbl_api.models.responses.User;
import com.example.ip_etfbl_api.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info/{name}")
    public User getUserInfoByUsername(@PathVariable String name)
    {
        return userService.findByUsername(name);
    }

}
