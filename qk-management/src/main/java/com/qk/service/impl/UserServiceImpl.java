package com.qk.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qk.common.PageResult;
import com.qk.dto.UserDto;
import com.qk.entity.User;
import com.qk.mapper.UserMapper;
import com.qk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询用户
     *
     * @param userDto
     * @return
     */
    @Override
    public PageResult<User> getUserList(UserDto userDto) {
        //开始分页
        PageHelper.startPage(userDto.getPage(), userDto.getPageSize());
        //调用mapper
        List<User> list = userMapper.getUserList(userDto);
        //封装数据
        PageInfo<User> userPageInfo = new PageInfo<>(list);
        return new PageResult<>(userPageInfo.getTotal(), userPageInfo.getList());
    }
}
