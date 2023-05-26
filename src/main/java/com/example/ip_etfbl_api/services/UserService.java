package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.base.CrudService;
import com.example.ip_etfbl_api.models.requests.UserInfoRequest;
import com.example.ip_etfbl_api.models.responses.User;
import com.example.ip_etfbl_api.models.responses.UserInfo;

public interface UserService extends CrudService<Integer> {
    <T> T findByUsername(String username, Class<T> resultDto);
    UserInfo updateUser(UserInfoRequest request, String currentUsername);
    void updatePassword(String username, String currentPassword, String newPassword);
}
