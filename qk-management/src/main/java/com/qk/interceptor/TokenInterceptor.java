package com.qk.interceptor;

import com.qk.utils.CurrentUserHoler;
import com.qk.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 令牌校验拦截器
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 在请求处理之前进行令牌校验
     * @param request 当前请求
     * @param response 当前响应
     * @param handler 被调用的处理器
     * @return true表示放行，false表示拦截
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求路径 URI /login
        String requestURI = request.getRequestURI();
        
        // 2.判断是否是登录请求
        if ("/login".equals(requestURI)) {
            log.info("当前是登录接口,直接放行");
            return true;
        }
        
        // 3.获取请求头(token)
        String token = request.getHeader("token");
        
        // 4.判断请求头是否是空 空-->响应401状态码
        if (token == null || token.isEmpty()) {
            log.info("token为空,请登录");
            response.setStatus(401);
            return false;
        }
        
        // 5.解析token  失败 ->响应401状态码
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer userid = (Integer) claims.get("userid");
            CurrentUserHoler.setCurrentUser(userid);
        } catch (Exception e) {
            log.error("token解析失败");
            response.setStatus(401);
            return false;
        }
        // 6.解析成功-->放行
        log.info("token校验成功,放行");
        return true;
    }
    //视图渲染完毕后执行，最后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        CurrentUserHoler.removeCurrentUser();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
