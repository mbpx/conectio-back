package com.amx.conectio.imagen;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;

@Service
public class ImagenService {

    @Autowired
    private ImagenRepository imagenDataRepository;

    public ImagenUploadResponse uploadImage(MultipartFile file) throws IOException {

        imagenDataRepository.save(Imagen.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImagenUtil.compressImage(file.getBytes())).build());

        return new ImagenUploadResponse("Image uploaded successfully: " +
                file.getOriginalFilename());

    }

    @Transactional
    public Imagen getInfoByImageByName(String name) {
        Optional<Imagen> dbImage = imagenDataRepository.findByName(name);

        return Imagen.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .imageData(ImagenUtil.decompressImage(dbImage.get().getImageData())).build();

    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<Imagen> dbImage = imagenDataRepository.findByName(name);
        byte[] image = ImagenUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }


}
