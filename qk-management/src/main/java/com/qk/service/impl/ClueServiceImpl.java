package com.qk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.entity.Clue;
import com.qk.entity.ClueTrackRecord;
import com.qk.mapper.ClueMapper;
import com.qk.mapper.ClueTrackRecordMapper;
import com.qk.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue> implements ClueService {
    @Autowired
    private ClueTrackRecordMapper clueTrackRecordMapper;

    /**
     * 根据id查询线索
     *
     * @param id
     * @return
     */
    @Override
    public Clue getClueById(Integer id) {
        return this.baseMapper.getClueById();
    }

    @Override
    public void trackClue(Clue clue) {
        //1.更新线索基础信息
        clue.setStatus(3);
        clue.setUpdateTime(LocalDateTime.now());
        this.baseMapper.updateById(clue);

        //2.新增一条跟进记录
        ClueTrackRecord clueTrackRecord = new ClueTrackRecord();
        clueTrackRecord.setClueId(clue.getId());
        clueTrackRecord.setUserId(1);
        clueTrackRecord.setSubject(clue.getSubject());
        clueTrackRecord.setLevel(clue.getLevel());
        clueTrackRecord.setRecord(clue.getRecord());
        clueTrackRecord.setNextTime(clue.getNextTime());
        clueTrackRecord.setType(1);
        clueTrackRecord.setCreateTime(LocalDateTime.now());
        clueTrackRecordMapper.insert(clueTrackRecord);
    }
}
