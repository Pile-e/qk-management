package com.qk.service.impl;

import com.qk.mapper.BusinessMapper;
import com.qk.mapper.ClueMapper;
import com.qk.service.ReportService;
import com.qk.vo.OverviewVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ReportServcieImpl implements ReportService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @Override
    public OverviewVO report() {
        //1.去redis中查找数据
        Object overview_data = redisTemplate.opsForValue().get("OVERVIEW_DATA");
        //2.判断数据是否为空
        if (overview_data != null) {
            return (OverviewVO) overview_data;
        }

        //3.若数据不存在则去数据库中查询
        //3.1. 线索相关数据查询
        OverviewVO overviewVOC = clueMapper.getClueOverviewData();
        //3.2. 商机相关数据查询
        OverviewVO overviewVOB = businessMapper.getBusinessOverviewData();
        //3.3. 合并数据
        BeanUtils.copyProperties(overviewVOB, overviewVOC, "clueTotal", "clueWaitAllot", "clueWaitFollow", "clueFollowing", "clueFalse", "clueConvertBusiness");

        //4.把数据库中查询到的数据存储到redis中
        redisTemplate.opsForValue().set("OVERVIER_DATA", overviewVOC, 1, TimeUnit.MINUTES);
        return overviewVOC;
    }
}
