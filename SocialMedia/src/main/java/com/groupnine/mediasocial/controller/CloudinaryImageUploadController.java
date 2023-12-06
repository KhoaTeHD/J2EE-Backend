package com.groupnine.mediasocial.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.groupnine.mediasocial.service.CloudinaryService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cloudinary")
public class CloudinaryImageUploadController {
	
	@Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<Map> uploadImage(@RequestParam("image")MultipartFile file){
        Map data = this.cloudinaryService.upload(file);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    
    @PostMapping("/delete/{url}")
    public ResponseEntity<Map> deleteImage(@PathVariable String url){
        Map data = this.cloudinaryService.delete(url);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}

