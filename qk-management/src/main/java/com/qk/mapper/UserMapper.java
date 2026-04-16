package com.qk.mapper;

import com.qk.dto.UserDto;
import com.qk.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 分页查询用户
     *
     * @param userDto
     * @return
     */
    List<User> getUserList(UserDto userDto);

    /**
     * 新增用户
     *
     * @param user
     */
    @Insert("insert into user(username, password, name, phone, email, gender, status, dept_id, role_id, image, remark, create_time, update_time) " +
            "values(#{username},#{password},#{name},#{phone},#{email},#{gender},#{status},#{deptId},#{roleId},#{image},#{remark},#{createTime},#{updateTime}) ")
    void addUser(User user);

    /**
     * 根据id删除用户
     *
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    User getById(Integer id);

    /**
     * 根据角色查询用户
     *
     * @param roleLabel
     * @return
     */

    @Select("select * from user where role_id = (select id from role where label=#{roleLabel})")
    List<User> getByRole(String roleLabel);
}
