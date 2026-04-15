package com.qk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qk.common.PageResult;
import com.qk.entity.Role;
import com.qk.mapper.RoleMapper;
import com.qk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 分页查询角色
     *
     * @param name
     * @param label
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageResult<Role> roleList(String name, String label, Integer page, Integer pageSize) {
        //开启分页查询
        PageHelper.startPage(page, pageSize);
        //调用mapper
        List<Role> list = roleMapper.roleList(name, label, page, pageSize);
        //封装数据
        PageInfo pageInfo = new PageInfo(list);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 根据id删除角色
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        roleMapper.deleteById(id);
    }

    /**
     * 新增角色
     *
     * @param role
     */
    @Override
    public void insertRole(Role role) {
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insertRole(role);
    }

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    @Override
    public Role selectById(Integer id) {
        Role role = roleMapper.selectById(id);
        return role;
    }

    /**
     * 更新角色
     *
     * @param role
     */
    @Override
    public void updateRole(Role role) {
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateRole(role);
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<Role> getAllRole() {
        List<Role> list = roleMapper.getAllRole();
        return list;
    }
}
