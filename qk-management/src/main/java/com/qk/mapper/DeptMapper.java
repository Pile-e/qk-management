package com.qk.mapper;

import com.qk.entity.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 新增部门信息
     *
     * @param dept
     */
    @Insert("insert into dept(name, status, create_time, update_time) values (#{name}, #{status}, #{createTime}, #{updateTime})")
    void addDept(Dept dept);

    /**
     * 查询部门信息
     *
     * @param name
     * @param status
     * @return
     */
    List<Dept> selectDept(String name, Integer status);

    @Select("select id, name, status, create_time, update_time from dept where id=#{id}")
    Dept getDeptById(Integer id);

    //@Update("update dept set name=#{name},status=#{status},update_time=#{updateTime} where id=#{id}")
    void updateDept(Dept dept);

    @Delete("delete from dept where id=#{id}")
    void deleteDeptById(Integer id);

    @Select("select id, name, status, create_time, update_time from dept")
    List<Dept> selectAllDept();
}
