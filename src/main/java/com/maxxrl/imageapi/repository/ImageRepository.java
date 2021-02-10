package com.maxxrl.imageapi.repository;

import com.maxxrl.imageapi.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}