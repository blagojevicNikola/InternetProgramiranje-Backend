package com.example.ip_etfbl_api.controllers;

import com.example.ip_etfbl_api.models.requests.AuthRequest;
import com.example.ip_etfbl_api.services.AuthenticationService;
import com.example.ip_etfbl_api.services.LogsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.io.IOException;


@RestController
@RequestMapping("/logs")
public class LogsController {

    private final AuthenticationService authenticationService;
    private final LogsService logsService;

    public LogsController(AuthenticationService authenticationService, LogsService logsService) {
        this.authenticationService = authenticationService;
        this.logsService = logsService;
    }

    @PostMapping (name = "", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getLogs(@RequestBody AuthRequest req) throws IOException {
        authenticationService.authenticateAdmin(req);
        return ResponseEntity.ok(logsService.readLogs());
    }

}
