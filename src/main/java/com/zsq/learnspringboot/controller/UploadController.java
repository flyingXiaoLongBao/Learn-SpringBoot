package com.zsq.learnspringboot.controller;

import com.zsq.learnspringboot.pojo.Result;
import com.zsq.learnspringboot.utils.MinioUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {
    
    /**
     * 上传文件接口
     */
    @PostMapping
    public Result<String> upload(MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                return Result.error("文件不能为空");
            }
            
            String url = MinioUtil.uploadAndGetUrl(file);
            return Result.success(url);
        } catch (Exception e) {
            return Result.error("文件上传失败: " + e.getMessage());
        }
    }
}