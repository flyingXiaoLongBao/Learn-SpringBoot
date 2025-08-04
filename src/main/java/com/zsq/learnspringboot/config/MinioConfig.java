package com.zsq.learnspringboot.config;


import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Bean
    public MinioClient minioClient(@Value("${minio.endpoint}") String endpoint,
                                   @Value("${minio.access-key}") String ak,
                                   @Value("${minio.secret-key}") String sk) {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(ak, sk)
                .build();
    }
}
