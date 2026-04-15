package com.qk.service;

import com.qk.common.PageResult;
import com.qk.dto.UserDto;
import com.qk.entity.User;

public interface UserService {
    PageResult<User> getUserList(UserDto userDto);
}
