package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.UserDto;
import com.qk.entity.User;
import com.qk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping
    public Result addUser(@RequestBody User user) {
        userService.addUser(user);
        return Result.success();
    }

    /**
     * 根据id删除用户
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids) {
        log.info("删除用户,ids:{}", ids);
        userService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @PutMapping
    public Result updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success();
    }

    /**
     * 根据角色查询用户
     *
     * @param roleLabel
     * @return
     */
    @GetMapping("/role/{roleLabel}")
    public Result getByRole(@PathVariable String roleLabel) {
        List<User> list = userService.getByRole(roleLabel);
        return Result.success(list);
    }
}
