package com.korea.WhereToGo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;

@Configuration
public class SftpConfig {

    @Value("${spring.sftp.host}")
    private String host;

    @Value("${spring.sftp.port}")
    private int port;

    @Value("${spring.sftp.username}")
    private String username;

    @Value("${spring.sftp.password}")
    private String password;

    @Value("${spring.sftp.remote.directory}")
    private String remoteDirectory;

    @Bean
    public DefaultSftpSessionFactory sftpSessionFactory() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUser(username);
        factory.setPassword(password);
        factory.setAllowUnknownKeys(true);
        return factory;
    }

    @Bean
    public String remoteDirectory() {
        return remoteDirectory;
    }
}


