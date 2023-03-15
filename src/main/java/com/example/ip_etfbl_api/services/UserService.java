package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.base.CrudService;
import com.example.ip_etfbl_api.models.responses.User;

public interface UserService extends CrudService<Integer> {
    User findByUsername(String username);
}
