package com.pa.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;
import com.pa.entity.GameImg;
import com.pa.service.GameImgService;
import com.cloudinary.Cloudinary;

@Service
public class GameImgServiceImpl implements GameImgService {
    @Autowired
    private Cloudinary cloudinary;

    // UPLOAD TO CLOUD
    public String uploadToCloudinary(File file) throws IOException {
        try {
        	// Use CloudinaryService to upload files to Cloudinary
        	Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            String fileUrl = (String) uploadResult.get("url");
            return fileUrl;
        } catch (IOException e) {
        	throw new IOException("Error uploading file");
        }
    }

    // SAVE IMG TO DATABASE AFTER UPLOAD TO
    @Override
    public List<GameImg> uploadImage(MultipartFile[] files) throws IOException {
        List<GameImg> images = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty() || !file.getOriginalFilename().endsWith(".jpg")) {
                continue;
            }

            try {
                // Upload files directly from MultipartFile to Cloudinary
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                String fileUrl = (String) uploadResult.get("url");
                images.add(new GameImg(fileUrl));
            } catch (IOException e) {
                throw new IOException("Error uploading file: " + file.getOriginalFilename());
            }
        }

        return images;
    }
}

