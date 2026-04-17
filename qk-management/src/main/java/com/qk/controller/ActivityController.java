package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Activity;
import com.qk.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 活动列表查询
     *
     * @param channel
     * @param type
     * @param status
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping
    public Result activityList(@RequestParam(required = false) Integer channel,
                               @RequestParam(required = false) Integer type,
                               @RequestParam(required = false) Integer status,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("接收参数 渠道来源：{},活动类型：{},状态：{},页码：{},每页记录数：{}", channel, type, status, page, pageSize);
        PageResult<Activity> pageResult = activityService.activityList(channel, type, status, page, pageSize);
        return Result.success(pageResult);
    }
}
