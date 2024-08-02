package com.korea.WhereToGo.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface SftpFileService {
    String upload(MultipartFile file);
    Resource getImage(String filename);
}
