package com.qk.mapper;

import com.qk.common.Result;
import com.qk.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {

    /**
     * 分页查询课程信息
     *
     * @param name
     * @param subject
     * @param target
     * @return
     */
    List<Course> courseList(String name, Integer subject, Integer target);

    /**
     * 根据id删除课程
     *
     * @param id
     */
    @Delete("delete from course where id=#{id}")
    void deleteCourseById(Integer id);

    /**
     * 新增课程
     *
     * @param course
     */
    @Insert("insert into course(subject, name, price, target, description, create_time, update_time) values (#{subject},#{name},#{price},#{target},#{description},#{createTime},#{updateTime}) ")
    void addCourse(Course course);

    /**
     * 根据id查询课程
     *
     * @param id
     * @return
     */
    @Select("select * from course where id=#{id}")
    Course getCourseById(Integer id);

    /**
     * 更新课程
     *
     * @param course
     */
    void updateCourse(Course course);

    /**
     * 查询所有课程
     *
     * @return
     */
    @Select("select * from course")
    List<Result> getAllCourse();

    /**
     * 根据学科查询课程
     *
     * @param subject
     * @return
     */
    @Select("select * from course where subject=#{subject}")
    List<Course> selectCourseBySubject(Integer subject);
}
