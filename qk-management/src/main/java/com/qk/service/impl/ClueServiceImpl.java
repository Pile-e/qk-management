package com.qk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.entity.Clue;
import com.qk.mapper.ClueMapper;
import com.qk.service.ClueService;
import org.springframework.stereotype.Service;

@Service
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue> implements ClueService {

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
}
