package com.qk.service;

import com.qk.common.PageResult;
import com.qk.dto.UserDto;
import com.qk.entity.User;
import com.qk.vo.LoginResultVo;

import java.util.List;

public interface UserService {
    PageResult<User> getUserList(UserDto userDto);

    void addUser(User user);

    void deleteByIds(List<Integer> ids);

    void updateUser(User user);

    User getById(Integer id);

    List<User> getByRole(String roleLabel);

    LoginResultVo login(User user);
}
