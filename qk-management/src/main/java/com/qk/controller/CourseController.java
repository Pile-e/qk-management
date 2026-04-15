package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Course;
import com.qk.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

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
    @GetMapping
    public Result courseList(@RequestParam(required = false) String name,
                             @RequestParam(required = false) Integer subject,
                             @RequestParam(required = false) Integer target,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<Course> pageResult = courseService.courseList(name, subject, target, page, pageSize);
        return Result.success(pageResult);
    }

    /**
     * 根据id删除课程
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteCourseById(@PathVariable Integer id) {
        courseService.deleteCourseById(id);
        return Result.success();
    }

    /**
     * 添加课程
     *
     * @param course
     * @return
     */
    @PostMapping
    public Result addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
        return Result.success();
    }

    /**
     * 根据id查询课程
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getCourseById(@PathVariable Integer id) {
        Course course = courseService.getCourseById(id);
        return Result.success(course);
    }

    /**
     * 更新课程
     *
     * @param course
     * @return
     */
    @PutMapping
    public Result updateCourse(@RequestBody Course course) {
        courseService.updateCourse(course);
        return Result.success();
    }

    /**
     * 查询所有课程数据
     *
     * @return
     */
    @GetMapping("/list")
    public Result getAllCourse() {
        List<Result> list = courseService.getAllCourse();
        return Result.success(list);
    }

    @GetMapping("/subject/{subject}")
    public Result selectCourseBySubject(@PathVariable Integer subject) {
        List<Course> list = courseService.selectCourseBySubject(subject);
        return Result.success(list);
    }
}
