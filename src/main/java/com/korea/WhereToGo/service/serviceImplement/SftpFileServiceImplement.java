package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.service.SftpFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SftpFileServiceImplement implements SftpFileService {

    private final DefaultSftpSessionFactory sftpSessionFactory;

    @Value("${spring.sftp.remote.directory}")
    private String remoteDirectory;

    @Override
    public String upload(MultipartFile file) {
        if (file.isEmpty()) return null;

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;

        try (SftpSession session = sftpSessionFactory.getSession()) {
            InputStream inputStream = file.getInputStream();
            session.write(inputStream, remoteDirectory + saveFileName);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return "http://15.165.24.165:8088/sftp/file/" + saveFileName;
    }

    @Override
    public Resource getImage(String filename) {
        try {
            SftpSession session = sftpSessionFactory.getSession();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            session.read(remoteDirectory + filename, outputStream);
            InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            return new InputStreamResource(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
