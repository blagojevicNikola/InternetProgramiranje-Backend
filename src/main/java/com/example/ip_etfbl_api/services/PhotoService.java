package com.example.ip_etfbl_api.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoService {

    public List<String> savePhotos(List<MultipartFile> photos) throws IOException;


}
