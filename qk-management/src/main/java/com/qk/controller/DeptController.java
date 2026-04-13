package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Dept;
import com.qk.mapper.DeptMapper;
import com.qk.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeptController {
    @Autowired
    DeptService deptService;
    @Autowired
    private DeptMapper deptMapper;

    /**
     * 新增部门
     *
     * @param dept - 部门信息
     * @return
     */
    @PostMapping("/depts")
    public Result addDept(@RequestBody Dept dept) {
        System.out.println("接收参数" + dept);
        deptService.addDept(dept);
        return Result.success();
    }

    /**
     * 查询部门数据
     *
     * @param name
     * @param status
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/depts")
    public Result selectDept(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "status", required = false) Integer status,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        System.out.println("接收参数" + "name: " + name + " status:" + status + " page:" + page + " pageSize:" + pageSize);
        PageResult<Dept> result = deptService.selectDept(name, status, page, pageSize);
        return Result.success(result);
    }

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     */
    @GetMapping("depts/{id}")
    public Result getDeptById(@PathVariable Integer id) {
        Dept dept = deptService.getDeptById(id);
        return Result.success(dept);
    }

    /**
     * 更新部门信息
     *
     * @param dept
     * @return
     */
    @PutMapping("depts")
    public Result updateDept(@RequestBody Dept dept) {
        deptService.updateDept(dept);
        return Result.success();
    }

    @DeleteMapping("depts/{id}")
    public Result deleteDeptById(@PathVariable Integer id) {
        deptService.deleteDeptById(id);
        return Result.success();
    }


}
