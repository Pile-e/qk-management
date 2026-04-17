package com.qk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qk.common.PageResult;
import com.qk.entity.Activity;
import com.qk.mapper.ActivityMapper;
import com.qk.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

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
    @Override
    public PageResult<Activity> activityList(Integer channel, Integer type, Integer status, Integer page, Integer pageSize) {
        //开启分页查询
        PageHelper.startPage(page, pageSize);
        //调用mapper
        List<Activity> list = activityMapper.activityList(channel, type, status);
        //封装数据
        PageInfo<Activity> activityPageInfo = new PageInfo<>(list);
        return new PageResult<>(activityPageInfo.getTotal(), activityPageInfo.getList());
    }
}
