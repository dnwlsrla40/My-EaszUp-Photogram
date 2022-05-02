package com.cos.photogramstart.dto.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Data
public class ImageUploadDto {
    private MultipartFile file;
    private String caption;
}
