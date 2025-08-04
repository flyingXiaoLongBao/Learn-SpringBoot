package com.zsq.learnspringboot.utils;


import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioStaticInitializer {

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initMinioUtil(
            MinioClient minioClient,
            @Value("${minio.bucket-name}") String bucket) {
        return event -> MinioUtil.setUp(minioClient, bucket);
    }
}