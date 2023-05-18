package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.services.PhotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Value("${file.photo-dir}")
    private String photosDir;

    @Override
    public List<String> savePhotos(List<MultipartFile> photos) throws IOException {
        List<String> urls = new ArrayList<String>();
        for (MultipartFile photo : photos) {
            String fileName = UUID.randomUUID().toString() + "-" + photo.getOriginalFilename();
            String filePath = photosDir + File.separator + fileName;
            photo.transferTo(new File(filePath));
            urls.add(filePath);
        }
        return urls;
    }
}
