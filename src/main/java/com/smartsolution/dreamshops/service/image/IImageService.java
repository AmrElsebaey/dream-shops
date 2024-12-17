package com.smartsolution.dreamshops.service.image;

import com.smartsolution.dreamshops.dto.ImageDto;
import com.smartsolution.dreamshops.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> addImages(List<MultipartFile> files, Long productId);
    Image updateImage(MultipartFile file, Long imageId);

}
