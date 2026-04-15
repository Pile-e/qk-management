package com.qk.mapper;

import com.qk.dto.UserDto;
import com.qk.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 分页查询用户
     *
     * @param userDto
     * @return
     */
    List<User> getUserList(UserDto userDto);
}
