package com.qk.service;

import com.qk.common.PageResult;
import com.qk.entity.Role;

import java.util.List;

public interface RoleService {

    PageResult<Role> roleList(String name, String label, Integer page, Integer pageSize);

    void deleteById(Integer id);

    void insertRole(Role role);

    Role selectById(Integer id);

    void updateRole(Role role);

    List<Role> getAllRole();
}
