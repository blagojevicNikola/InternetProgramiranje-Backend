package com.example.ip_etfbl_api.models.requests;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeRequest {
    private String name;
    private String value;
}
