package com.qk.mapper;

import com.qk.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {

    /**
     * 分页查询角色
     *
     * @param name
     * @param label
     * @param page
     * @param pageSize
     * @return
     */
    List<Role> roleList(String name, String label, Integer page, Integer pageSize);

    /**
     * 根据id删除角色
     *
     * @param id
     */
    @Delete("delete from role where id=#{id}")
    void deleteById(Integer id);

    /**
     * 新增角色
     *
     * @param role
     */
    @Insert("insert into role(name, label, remark, create_time, update_time) VALUES (#{name},#{label},#{remark},#{createTime},#{updateTime})")
    void insertRole(Role role);

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    @Select("select id, name, label, remark, create_time, update_time from role where id=#{id}")
    Role selectById(Integer id);

    /**
     * 更新角色
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * 查询所有角色
     *
     * @return
     */
    @Select("select * from role")
    List<Role> getAllRole();
}
