package com.example.ip_etfbl_api.models.requests;

import lombok.Data;

@Data
public class NewPasswordRequest {
    private String currentPassword;
    private String newPassword;
}
