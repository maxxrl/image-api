package com.maxxrl.imageapi.service;

import com.maxxrl.imageapi.model.Image;
import com.maxxrl.imageapi.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ImageStorageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image saveFile(MultipartFile file) {
        String name = file.getOriginalFilename();
        try {
            Image image = new Image(name, file.getContentType(), file.getBytes());
            return imageRepository.save(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<Image> getFile(Long fileId) {
        return imageRepository.findById(fileId);
    }

    public List<Image> getFiles() {
        return imageRepository.findAll();
    }
}
