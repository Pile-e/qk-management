package com.qk.service;

import com.qk.common.PageResult;
import com.qk.entity.Dept;

import java.util.List;

public interface DeptService {
    void addDept(Dept dept);

    PageResult selectDept(String name, Integer status, Integer page, Integer pageSize);

    Dept getDeptById(Integer id);

    void updateDept(Dept dept);

    void deleteDeptById(Integer id);

    List<Dept> selectAllDept();
}
