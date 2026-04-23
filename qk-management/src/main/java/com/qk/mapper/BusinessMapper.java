package com.qk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.dto.BusinessDto;
import com.qk.entity.Business;
import com.qk.vo.OverviewVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessMapper extends BaseMapper<Business> {
    OverviewVO getBusinessOverviewData();

    Page<Business> businessList(Page<Object> objectPage, BusinessDto businessDto);
}
