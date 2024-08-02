package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.service.SftpFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/sftp/file")
@RequiredArgsConstructor
public class SftpFileController {

    private final SftpFileService sftpFileService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String url = sftpFileService.upload(file);
        return url;
    }

    @GetMapping(value = "{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<Resource> getImage(@PathVariable("fileName") String fileName) {
        Resource resource = sftpFileService.getImage(fileName);
        if (resource != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("image/jpeg")) // 필요에 따라 MIME 타입 조정
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
