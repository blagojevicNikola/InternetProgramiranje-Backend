package com.example.ip_etfbl_api.security.models;

import jakarta.annotation.sql.DataSourceDefinitions;
import lombok.Data;

import java.util.List;

@Data
public class AuthorizationRules {
    private List<Rule> rules;
}
