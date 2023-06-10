package com.example.ip_etfbl_api.models.requests;

import lombok.Data;

@Data
public class ActivateRequest {
    private String username;
    private Integer pin;
}
