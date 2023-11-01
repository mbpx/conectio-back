package com.amx.conectio.imagen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/imagen")
public class ImagenController {

    @Autowired
    private ImagenService imagenDataService;

	@GetMapping
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("hello imagen world");
	}
    
    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("img") MultipartFile file) throws IOException {
        ImagenUploadResponse response = imagenDataService.uploadImage(file);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/info/{name}")
    public ResponseEntity<?>  getImageInfoByName(@PathVariable("name") String name){
        Imagen image = imagenDataService.getInfoByImageByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?>  getImageByName(@PathVariable("name") String name){
        byte[] image = imagenDataService.getImage(name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }


}