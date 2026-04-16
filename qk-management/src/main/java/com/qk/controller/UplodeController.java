package com.qk.controller;

import com.qk.common.Result;
import com.qk.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
public class UplodeController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result uploadAliyun(MultipartFile image) throws Exception {
        log.info("上传文件:{}", image);
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        String url = aliyunOSSOperator.upload(image.getBytes(), fileName);
        return Result.success(url);
    }

    /**
     * 本地存储文件
     * 关联src/main/resources/static/upload.html
     *
     * @param username
     * @param age
     * @param file
     * @return
     */
    /*@PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile file) throws IOException {
        log.info("上传文件,参数{},{},{}", username, age, file);
        //获取文件名
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        file.transferTo(new File("D:\\develop\\IDEAcode\\qk-parent\\file\\" + fileName));
        return Result.success();
    }*/
}
