package com.qk.service;

import com.qk.common.PageResult;
import com.qk.entity.Activity;

public interface ActivityService {
    PageResult<Activity> activityList(Integer channel, Integer type, Integer status, Integer page, Integer pageSize);

    void deleteActById(Integer id);
}
