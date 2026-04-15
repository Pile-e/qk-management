package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Role;
import com.qk.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public Result roleList(@RequestParam(required = false) String name,
                           @RequestParam(required = false) String label,
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<Role> pageResult = roleService.roleList(name, label, page, pageSize);
        return Result.success(pageResult);
    }

    /**
     * 根据id删除角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        roleService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @PostMapping
    public Result insertRole(@RequestBody Role role) {
        roleService.insertRole(role);
        return Result.success();
    }

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        Role role = roleService.selectById(id);
        return Result.success(role);
    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @PutMapping
    public Result updateRole(@RequestBody Role role) {
        roleService.updateRole(role);
        return Result.success();
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @GetMapping("/list")
    public Result getAllRole() {
        List<Role> list = roleService.getAllRole();
        return Result.success(list);
    }
}
