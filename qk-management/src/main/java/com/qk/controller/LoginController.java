package com.qk.controller;

import com.qk.common.Result;
import com.qk.entity.User;
import com.qk.service.UserService;
import com.qk.vo.LoginResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("用户登录 {}", user);
        LoginResultVo loginResultVo = userService.login(user);
        if (loginResultVo == null) {
            return Result.error("登陆失败");
        }
        return Result.success(loginResultVo);
    }
}
