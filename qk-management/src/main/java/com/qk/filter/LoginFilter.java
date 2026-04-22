package com.qk.filter;

import com.qk.utils.CurrentUserHoler;
import com.qk.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 登录过滤器
 */
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

    // 拦截请求之后执行此方法
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        // 1.获取请求路径 URL https://localhost:8080/login URI /login
        String requestURI = httpServletRequest.getRequestURI();
        // 2.判断是否是登录请求
        if ("/login".equals(requestURI)) {
            log.info("当前是登录接口,直接放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 3.获取请求头(token)
        String token = httpServletRequest.getHeader("token");
        // 4.判断请求头是否是空 空-->响应401状态码
        if (token == null || token.isEmpty()) {
            log.info("token为空,请登录");
            httpServletResponse.setStatus(401);
            return;
        }
        // 5.解析token  失败 ->响应401状态码
        try {
            // 解析用户id之后,存储到threadlocal中
            Claims claims = JwtUtils.parseToken(token);
            Integer userid = (Integer) claims.get("userid");
            CurrentUserHoler.setCurrentUser(userid);
        } catch (Exception e) {
            log.error("token解析失败");
            httpServletResponse.setStatus(401);
            return;
        }
        // 6.解析成功-->放行
        filterChain.doFilter(servletRequest, servletResponse);
        // 7.响应处理完成之后,把threadlocal中的数据删除掉
        CurrentUserHoler.removeCurrentUser();
    }
}
