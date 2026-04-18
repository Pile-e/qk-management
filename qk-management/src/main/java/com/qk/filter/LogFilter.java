package com.qk.filter;

import com.qk.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //获取请求路径
        String requestURI = httpServletRequest.getRequestURI();

        //判断是否为登录路径
        if ("/login".equals(requestURI)) {
            log.info("当前是登录接口，直接放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //获取请求头token
        String token = httpServletRequest.getHeader("token");
        //判断请求头是否为空  空--> 401
        if (token == null || token.isEmpty()) {
            log.info("token为空，请登录");
            httpServletResponse.setStatus(401);
            return;
        }
        //解析token 失败--> 401
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.error("token解析失败");
            httpServletResponse.setStatus(401);
            return;
        }

        //解析成功 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }
    //拦截所有请求

}
