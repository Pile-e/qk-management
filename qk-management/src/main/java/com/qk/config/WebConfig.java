package com.qk.config;


import com.qk.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 标识当前是一个配置类
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private DemoInterceptor demoInterceptor;

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Demo拦截器
//        registry.addInterceptor(demoInterceptor).addPathPatterns("/**");

        // 注册令牌校验拦截器
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }
}
