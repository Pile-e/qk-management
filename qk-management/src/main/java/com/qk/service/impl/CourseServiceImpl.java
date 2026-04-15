package com.qk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Course;
import com.qk.mapper.CourseMapper;
import com.qk.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 分页查询课程信息
     *
     * @param name
     * @param subject
     * @param target
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageResult<Course> courseList(String name, Integer subject, Integer target, Integer page, Integer pageSize) {
        //开启分页
        PageHelper.startPage(page, pageSize);
        //调用mapper
        List<Course> list = courseMapper.courseList(name, subject, target);
        //封装数据
        PageInfo<Course> coursePageInfo = new PageInfo<>(list);
        return new PageResult<>(coursePageInfo.getTotal(), coursePageInfo.getList());
    }

    @Override
    public void deleteCourseById(Integer id) {
        courseMapper.deleteCourseById(id);
    }

    @Override
    public void addCourse(Course course) {
        course.setCreateTime(LocalDateTime.now());
        course.setUpdateTime(LocalDateTime.now());
        courseMapper.addCourse(course);
    }

    @Override
    public Course getCourseById(Integer id) {
        Course course = courseMapper.getCourseById(id);
        return course;
    }

    @Override
    public void updateCourse(Course course) {
        course.setUpdateTime(LocalDateTime.now());
        courseMapper.updateCourse(course);
    }

    @Override
    public List<Result> getAllCourse() {
        List<Result> list = courseMapper.getAllCourse();
        return list;
    }

    @Override
    public List<Course> selectCourseBySubject(Integer subject) {
        List<Course> list = courseMapper.selectCourseBySubject(subject);
        return list;
    }
}
