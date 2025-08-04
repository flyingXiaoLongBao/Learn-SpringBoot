package com.zsq.learnspringboot.utils;

import io.minio.*;
import io.minio.http.Method;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public final class MinioUtil {

    private static MinioClient CLIENT;
    private static String BUCKET;

    /** 禁止实例化 */
    private MinioUtil() {}

    /**
     * 由 Spring 容器在启动完成后调用一次，注入真正的 client 和 bucket 名
     */
    public static void setUp(MinioClient client, String bucket) {
        CLIENT = client;
        BUCKET = bucket;
    }

    public static boolean isInitialized() {
        return CLIENT != null;
    }

    /**
     * 上传并返回可直接访问的 URL（7 天有效期）
     */
    @SneakyThrows
    public static String uploadAndGetUrl(MultipartFile file) {
        if (CLIENT == null) {
            throw new IllegalStateException("MinioUtil 尚未初始化，请确认 Spring 已启动");
        }
        String ext = file.getOriginalFilename() == null ? "" :
                     file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String objectName = UUID.randomUUID() + ext;

        CLIENT.putObject(
                PutObjectArgs.builder()
                        .bucket(BUCKET)
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        return CLIENT.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(BUCKET)
                        .object(objectName)
                        .expiry(7, TimeUnit.DAYS)
                        .build()
        );
    }

    /* 其他工具方法可继续追加 */
}