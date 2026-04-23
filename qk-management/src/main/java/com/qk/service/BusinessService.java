package com.qk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.BusinessDto;
import com.qk.entity.Business;

public interface BusinessService extends IService<Business> {
    PageResult<Business> businessList(BusinessDto businessDto);

    Business seleteById(Integer id);
}
