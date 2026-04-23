package com.qk.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.dto.BusinessDto;
import com.qk.entity.Business;
import com.qk.mapper.BusinessMapper;
import com.qk.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    /**
     * 商机列表查询
     *
     * @param businessDto
     * @return
     */
    @Override
    public PageResult<Business> businessList(BusinessDto businessDto) {
        Page<Business> page = businessMapper.businessList(new Page<>(businessDto.getPage(), businessDto.getPageSize()), businessDto);
        return new PageResult<>(page.getTotal(), page.getRecords());
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Business seleteById(Integer id) {
        Business business = businessMapper.seleteById(id);
        return business;
    }
}
