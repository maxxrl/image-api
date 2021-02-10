package com.maxxrl.imageapi.controller;

import com.maxxrl.imageapi.model.Image;
import com.maxxrl.imageapi.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageStorageService imageStorageService;


    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {

            imageStorageService.saveFile(file);

        return ResponseEntity.ok().body("Image uploaded.");
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable Long fileId) {
        Image image = imageStorageService.getFile(fileId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + image.getName() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }

}
