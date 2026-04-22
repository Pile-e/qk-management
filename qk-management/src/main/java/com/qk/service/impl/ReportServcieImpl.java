package com.qk.service.impl;

import com.qk.mapper.BusinessMapper;
import com.qk.mapper.ClueMapper;
import com.qk.service.ReportService;
import com.qk.vo.OverviewVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServcieImpl implements ReportService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public OverviewVO report() {
        OverviewVO overviewVOC = clueMapper.getClueOverviewData();
        OverviewVO overviewVOB = businessMapper.getBusinessOverviewData();
        BeanUtils.copyProperties(overviewVOB, overviewVOC, "clueTotal", "clueWaitAllot", "clueWaitFollow", "clueFollowing", "clueFalse", "clueConvertBusiness");
        return overviewVOC;
    }
}
