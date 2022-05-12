package com.teamY.simple.simplyChat.controller;

import com.teamY.simple.simplyChat.utill.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class S3Controller {
    private final S3Uploader s3Uploader;

    // s3 적용하지 않기로 함
//    @PostMapping("/image")
//    public String updateUserImage(@RequestParam("images") MultipartFile multipartFile) {
//        String imageUrl = "error";
//        try {
//            imageUrl = s3Uploader.uploadFiles(multipartFile, "static");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return imageUrl;
//        }
//        return imageUrl;
//    }
}
