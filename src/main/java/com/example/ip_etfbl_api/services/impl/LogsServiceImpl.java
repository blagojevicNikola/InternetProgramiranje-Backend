package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.services.LogsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LogsServiceImpl implements LogsService {

    @Value("${logging.file.name}")
    private String logsPath;
    @Override
    public String readLogs() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(logsPath))))
        {
            List<String> lines = new ArrayList<>();
            String line;
            while((line = reader.readLine())!=null)
            {
                lines.add(line);
            }
            return lines.stream().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
