package com.qk.mapper;

import com.qk.entity.Activity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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

    /**
     * 根据id删除活动
     *
     * @param id
     */
    @Delete("delete from activity where id=#{id}")
    void deleteActById(Integer id);

    /**
     * 添加课程
     *
     * @param activity
     */
    @Insert("insert into activity(channel, name, start_time, end_time, description, type, discount, voucher, create_time, update_time) " +
            "values (#{channel},#{name},#{startTime},#{endTime},#{description},#{type},#{discount},#{voucher},#{createTime},#{updateTime})")
    void addAct(Activity activity);
}
