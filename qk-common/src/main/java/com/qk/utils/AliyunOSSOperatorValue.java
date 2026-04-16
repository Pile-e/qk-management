package com.qk.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

/**
 * 通过Value注解配置阿里云
 */
@Slf4j
@Component
public class AliyunOSSOperatorValue {

    @Value("${aliyun.oss.region}")
    private String region;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

//    private static final String REGION = "cn-beijing";
//    private static final String BUCKET_NAME = "ai-02";
//    private static final String ENDPOINT = "https://oss-cn-beijing.aliyuncs.com";
//    private static final String ACCESS_KEY_ID = "";
//    private static final String ACCESS_KEY_SECRET = "";

    public String upload(byte[] content, String objectName) throws Exception {
        // 创建OSSClient实例
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = null;
        DefaultCredentialProvider provider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        try {
            ossClient = OSSClientBuilder.create()
                    .endpoint(endpoint)
                    .credentialsProvider(provider)
                    .clientConfiguration(clientBuilderConfiguration)
                    .region(region)
                    .build();
            // 创建PutObjectRequest对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(content));
            // 上传文件
            ossClient.putObject(putObjectRequest);

            // 返回文件访问URL
            return "https://" + bucketName + "." + endpoint.substring(8) + "/" + objectName;
        } catch (Exception e) {
            log.error("Caught an OSSException: " + e.getMessage());
            throw e;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}