package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.base.CrudService;
import com.example.ip_etfbl_api.models.requests.UserInfoRequest;
import com.example.ip_etfbl_api.models.responses.User;
import com.example.ip_etfbl_api.models.responses.UserInfo;

public interface UserService extends CrudService<Integer> {
    User findByUsername(String username);
    UserInfo updateUser(UserInfoRequest request, String currentUsername);
}
