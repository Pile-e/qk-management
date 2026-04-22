package com.qk.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.dto.ClueDto;
import com.qk.entity.Business;
import com.qk.entity.Clue;
import com.qk.entity.ClueTrackRecord;
import com.qk.mapper.BusinessMapper;
import com.qk.mapper.ClueMapper;
import com.qk.mapper.ClueTrackRecordMapper;
import com.qk.service.ClueService;
import com.qk.utils.CurrentUserHoler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue> implements ClueService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private ClueTrackRecordMapper clueTrackRecordMapper;

    @Autowired
    private BusinessMapper businessMapper;

    /**
     * 根据id查询线索
     *
     * @param id
     * @return
     */
    @Override
    public Clue getClueById(Integer id) {
        return clueMapper.getClueById(id);
    }

    /**
     * 跟进线索
     *
     * @param clue
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void trackClue(Clue clue) {
        //1.更新线索基础信息
        clue.setStatus(3);
        clue.setUpdateTime(LocalDateTime.now());
        this.baseMapper.updateById(clue);

        //2.新增一条跟进记录
        ClueTrackRecord clueTrackRecord = new ClueTrackRecord();
        clueTrackRecord.setClueId(clue.getId());
        clueTrackRecord.setUserId(CurrentUserHoler.getCurrentUser());
        clueTrackRecord.setSubject(clue.getSubject());
        clueTrackRecord.setLevel(clue.getLevel());
        clueTrackRecord.setRecord(clue.getRecord());
        clueTrackRecord.setNextTime(clue.getNextTime());
        clueTrackRecord.setType(1);
        clueTrackRecord.setCreateTime(LocalDateTime.now());
        clueTrackRecordMapper.insert(clueTrackRecord);
    }

    /**
     * 列表查询
     *
     * @param clueDto
     * @return
     */
    @Override
    public PageResult<Clue> clueList(ClueDto clueDto) {
        Page<Clue> page = clueMapper.queryClueList(new Page<>(clueDto.getPage(), clueDto.getPageSize()), clueDto);
        return new PageResult<>(page.getTotal(), page.getRecords());
    }

    /**
     * 转商机
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toBusiness(Integer id) {
        //跟进前端数据查询对应数据
        Clue clue = this.baseMapper.selectById(id);
        //更新状态为转商机状态
        clue.setStatus(5);
        clue.setUpdateTime(LocalDateTime.now());
        this.baseMapper.updateById(clue);

        //新增一条商机数据
        Business business = new Business();
        //把前边对象的属性赋值给后边的 前提条件：两个对象的属性名是相等的
        BeanUtils.copyProperties(clue, business);
        business.setId(null);
        business.setStatus(1);
        business.setUserId(null);
        business.setClueId(clue.getId());
        business.setNextTime(null);
        business.setCreateTime(LocalDateTime.now());
        business.setUpdateTime(LocalDateTime.now());
        businessMapper.insert(business);
    }


}
