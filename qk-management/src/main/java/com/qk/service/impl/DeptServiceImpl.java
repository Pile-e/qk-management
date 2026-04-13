package com.qk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qk.common.PageResult;
import com.qk.entity.Dept;
import com.qk.mapper.DeptMapper;
import com.qk.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptMapper deptMapper;

    /**
     * 新增部门
     *
     * @param dept
     */
    @Override
    public void addDept(Dept dept) {
        //1.补全基础数据
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用mapper插入数据
        deptMapper.addDept(dept);
    }

    /**
     * 查询部门信息
     *
     * @param name
     * @param status
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageResult selectDept(String name, Integer status, Integer page, Integer pageSize) {
        //开启分页查询
        PageHelper.startPage(page, pageSize);
        //调用mapper查询数据
        List<Dept> list = deptMapper.selectDept(name, status);

        //封装数据

        //方式一：
//      Page<Dept> deptList = (Page<Dept>) list;
//      return new PageResult<>(deptList.getTotal(), deptList.getResult());
        //方式二
        PageInfo<Dept> deptPageInfo = new PageInfo<>(list);
        return new PageResult<>(deptPageInfo.getTotal(), deptPageInfo.getList());
    }

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     */
    @Override
    public Dept getDeptById(Integer id) {
        Dept dept = deptMapper.getDeptById(id);
        return dept;
    }

    /**
     * 更新部门信息
     *
     * @param dept
     */
    @Override
    public void updateDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }

    @Override
    public void deleteDeptById(Integer id) {
        deptMapper.deleteDeptById(id);
    }
}
