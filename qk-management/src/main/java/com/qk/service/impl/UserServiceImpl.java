package com.qk.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qk.common.PageResult;
import com.qk.dto.UserDto;
import com.qk.entity.User;
import com.qk.exception.BusinessException;
import com.qk.mapper.UserMapper;
import com.qk.service.UserService;
import com.qk.utils.JwtUtils;
import com.qk.vo.LoginResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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

    @Override
    public void addUser(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        String pwd = user.getName() + "123";
        user.setPassword(DigestUtils.md5DigestAsHex(pwd.getBytes()));
        userMapper.addUser(user);
    }

    /**
     * 根据id删除用户
     *
     * @param ids
     */
    @Override
    public void deleteByIds(List<Integer> ids) {
        userMapper.deleteByIds(ids);
    }

    /**
     * 更新用户信息
     *
     * @param user
     */
    @Override
    public void updateUser(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateUser(user);
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public User getById(Integer id) {
        User user = userMapper.getById(id);
        return user;
    }

    /**
     * 根据角色查询用户
     *
     * @param roleLabel
     * @return
     */
    @Override
    public List<User> getByRole(String roleLabel) {
        List<User> list = userMapper.getByRole(roleLabel);
        return list;
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    public LoginResultVo login(User user) {
        //在数据库中查询对应用户的信息
        User u = userMapper.selectUserByUsername(user.getUsername());
        //判断用户是否存在
        if (u == null) {
            log.info("用户不存在");
            throw new BusinessException("用户不存在");
        }
        //判断密码是否正确
        String pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        if (!u.getPassword().equals(pwd)) {
            log.info("密码错误");
            throw new BusinessException("密码错误");
        }
        //判断用户状态是否正常
        if (u.getStatus() != 1) {
            log.info("用户已停用");
            throw new BusinessException("用户已停用");
        }
        //封装数据对象
        LoginResultVo loginResultVo = new LoginResultVo();
        loginResultVo.setId(u.getId());
        loginResultVo.setUsername(u.getUsername());
        loginResultVo.setName(u.getName());
        loginResultVo.setImage(u.getImage());
        loginResultVo.setRoleLabel(u.getRoleLabel());

        Map<String, Object> tmpMap = new HashMap<>();
        tmpMap.put("userid", u.getId());
        tmpMap.put("username", u.getUsername());
        String token = JwtUtils.generateToken(tmpMap);

        loginResultVo.setToken(token);
        return loginResultVo;
    }
}
