package com.qk.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.dto.BusinessDto;
import com.qk.entity.Business;
import com.qk.entity.BusinessTrackRecord;
import com.qk.mapper.BusinessMapper;
import com.qk.mapper.BusinessTrackRecordMapper;
import com.qk.service.BusinessService;
import com.qk.utils.CurrentUserHoler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private BusinessTrackRecordMapper businessTrackRecordMapper;

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

    /**
     * 跟进商机
     *
     * @param business
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void trackBusiness(Business business) {
        //更新商机基础记录
        business.setStatus(3);
        business.setUpdateTime(LocalDateTime.now());
        this.baseMapper.updateById(business);

        //新增一条跟进记录
        BusinessTrackRecord businessTrackRecord = new BusinessTrackRecord();
        businessTrackRecord.setBusinessId(business.getId());
        businessTrackRecord.setUserId(CurrentUserHoler.getCurrentUser());
        businessTrackRecord.setTrackStatus(Integer.parseInt(business.getTrackStatus()));
        businessTrackRecord.setKeyItems(String.join(", ", business.getKeyItems()));
        businessTrackRecord.setNextTime(business.getNextTime());
        businessTrackRecord.setRecord(business.getRecord());
        businessTrackRecord.setCreateTime(LocalDateTime.now());
        businessTrackRecordMapper.insert(businessTrackRecord);
    }
}
