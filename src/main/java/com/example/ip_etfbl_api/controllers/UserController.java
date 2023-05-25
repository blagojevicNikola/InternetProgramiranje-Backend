package com.example.ip_etfbl_api.controllers;

import com.example.ip_etfbl_api.exceptions.NotFoundException;
import com.example.ip_etfbl_api.models.requests.UserInfoRequest;
import com.example.ip_etfbl_api.models.responses.User;
import com.example.ip_etfbl_api.models.responses.UserInfo;
import com.example.ip_etfbl_api.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/preview/{name}")
    public User getUserPreviewByUsername(@PathVariable String name)
    {
        return userService.findByUsername(name);
    }

    @GetMapping("/info/{name}")
    public UserInfo getUserInfoByUsername(@PathVariable String name) {
        return null;
    }
    @PutMapping("/update")
    public UserInfo updateUser(@RequestBody UserInfoRequest request, Authentication authentication)
    {
        String username = authentication.getName();
        return userService.updateUser(request, username);
    }

}
