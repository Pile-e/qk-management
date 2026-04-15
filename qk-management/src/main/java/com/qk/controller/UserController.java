package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.UserDto;
import com.qk.entity.User;
import com.qk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 分页查询用户
     *
     * @param userDto
     * @return
     */
    @GetMapping()
    public Result getUserList(UserDto userDto) {
        log.info("接收参数{}", userDto);
        //分页查询返回PageResult
        PageResult<User> pageResult = userService.getUserList(userDto);
        return Result.success(pageResult);
    }

}
