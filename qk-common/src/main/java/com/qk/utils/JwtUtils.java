package com.qk.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    // 秘钥
    private static final String SECRET_KEY = "cWluZ2tl";
    // 令牌有效期（12小时）
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000;

    /**
     * 生成JWT令牌
     *
     * @param claims 自定义声明信息
     * @return 生成的JWT令牌
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()  // 构建令牌
                .setClaims(claims)  // 设置载荷中的数据 map格式
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // 设置过期时间 (需要再设置数据之后设置才生效)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 签名秘钥和加密算法
                .compact();
    }

    /**
     * 解析JWT令牌
     *
     * @param token JWT令牌
     * @return 解析后的Claims对象
     */
    public static Claims parseToken(String token) {
        return Jwts.parser() // 解析令牌
                .setSigningKey(SECRET_KEY) // 设置秘钥
                .parseClaimsJws(token)  // 解析token
                .getBody();  // 获取载荷中的数据
    }

    public static void main(String[] args) throws InterruptedException {
        Map<String, Object> tmpMap = new HashMap<>();
        tmpMap.put("loginUserId","1");
        tmpMap.put("loginUserName","Tom");
        String token = JwtUtils.generateToken(tmpMap);
        System.out.println(token);

        Thread.sleep(2000);
        Claims claims = JwtUtils.parseToken(token);
        System.out.println(claims.getExpiration());
        System.out.println(claims.get("loginUserId"));
        System.out.println(claims.get("loginUserName"));
    }
}