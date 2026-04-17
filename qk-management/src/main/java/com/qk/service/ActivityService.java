package com.qk.service;

import com.qk.common.PageResult;
import com.qk.entity.Activity;

import java.util.List;

public interface ActivityService {
    PageResult<Activity> activityList(Integer channel, Integer type, Integer status, Integer page, Integer pageSize);

    void deleteActById(Integer id);

    void addAct(Activity activity);

    Activity getActById(Integer id);

    void updateAct(Activity activity);

    List<Activity> getTypeAct(Integer type);
}
