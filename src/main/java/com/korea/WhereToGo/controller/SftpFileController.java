package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.service.SftpFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sftp/file")
@RequiredArgsConstructor
public class SftpFileController {

    private final SftpFileService sftpFileService;

    private static final Map<String, String> MIME_TYPES = new HashMap<>();
    static {
        MIME_TYPES.put("jpg", MediaType.IMAGE_JPEG_VALUE);
        MIME_TYPES.put("jpeg", MediaType.IMAGE_JPEG_VALUE);
        MIME_TYPES.put("png", MediaType.IMAGE_PNG_VALUE);
        MIME_TYPES.put("gif", MediaType.IMAGE_GIF_VALUE);
        MIME_TYPES.put("bmp", "image/bmp");
        MIME_TYPES.put("tif", "image/tiff");
        MIME_TYPES.put("tiff", "image/tiff");
        MIME_TYPES.put("webp", "image/webp");
        MIME_TYPES.put("svg", "image/svg+xml");
        MIME_TYPES.put("heif", "image/heif");
        MIME_TYPES.put("heic", "image/heic");
        MIME_TYPES.put("ico", "image/x-icon");
        MIME_TYPES.put("apng", "image/apng");
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String url = sftpFileService.upload(file);
        return url;
    }

    @GetMapping(value = "{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE,
            "image/bmp", "image/tiff", "image/webp", "image/svg+xml", "image/heif", "image/heic", "image/x-icon", "image/apng"
    })
    public ResponseEntity<Resource> getImage(@PathVariable("fileName") String fileName) {
        Resource resource = sftpFileService.getImage(fileName);
        if (resource != null) {
            String fileExtension = getFileExtension(fileName).toLowerCase();
            String contentType = MIME_TYPES.getOrDefault(fileExtension, MediaType.APPLICATION_OCTET_STREAM_VALUE);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
