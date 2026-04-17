package com.qk.mapper;

import com.qk.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper {

    /**
     * 活动列表查询
     *
     * @param channel
     * @param type
     * @param status
     * @return
     */
    List<Activity> activityList(Integer channel, Integer type, Integer status);
}
