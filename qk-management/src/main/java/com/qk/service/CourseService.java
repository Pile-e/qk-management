package com.qk.service;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Course;

import java.util.List;

public interface CourseService {
    PageResult<Course> courseList(String name, Integer subject, Integer target, Integer page, Integer pageSize);

    void deleteCourseById(Integer id);

    void addCourse(Course course);

    Course getCourseById(Integer id);

    void updateCourse(Course course);

    List<Result> getAllCourse();

    List<Course> selectCourseBySubject(Integer subject);
}
