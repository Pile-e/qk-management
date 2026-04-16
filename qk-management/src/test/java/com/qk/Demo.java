package com.qk;

public class Demo {

    public static void main(String[] args) throws Exception {
//        // 从环境变量获取访问凭证
//        String accessKeyId = "";
//        String accessKeySecret = "" ;
//        // 要上传的文件路径 示例(C:\Users\Lenovo\Pictures\24.png)
//        String filePath = "D:\\develop\\IDEAcode\\qk-parent\\file\\iiddeeaa.png";
//
//        // region 填写Bucket所在地域。以北京1（北京）为例，Region示例为(cn-beijing)
//        String region = "cn-beijing";
//        // Endpoint以华北1（北京）为例，示例 (https://oss-cn-beijing.aliyuncs.com)
//        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
//
//        // 设置要上传的bucket桶名称 示例 ai-01
//        String bucketName = "ai-02";
//        // 设置要上传到桶中的文件位置,相对路径开始,示例 upload/1.png
//        String objectName = "iiddeeaa.png";
//
//        // 创建凭证提供者
//        DefaultCredentialProvider provider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
//
//        // 配置客户端参数
//        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
//        // 显式声明使用V4签名算法
//        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
//
//        // 初始化OSS客户端
//        OSS ossClient = OSSClientBuilder.create()
//                .credentialsProvider(provider)
//                .clientConfiguration(clientBuilderConfiguration)
//                .region(region)
//                .endpoint(endpoint)
//                .build();
//
//        try {
//            // 填写字符串。
//            byte[] content = Files.readAllBytes(Paths.get(filePath));
//
//            // 创建PutObjectRequest对象。
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(content));
//
//            // 上传字符串。
//            PutObjectResult result = ossClient.putObject(putObjectRequest);
//        } catch (OSSException oe) {
//            System.out.println("Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
//            System.out.println("Error Message:" + oe.getErrorMessage());
//            System.out.println("Error Code:" + oe.getErrorCode());
//            System.out.println("Request ID:" + oe.getRequestId());
//            System.out.println("Host ID:" + oe.getHostId());
//        } catch (ClientException ce) {
//            System.out.println("Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network.");
//            System.out.println("Error Message:" + ce.getMessage());
//        } finally {
//            if (ossClient != null) {
//                ossClient.shutdown();
//            }
//        }
    }
}   