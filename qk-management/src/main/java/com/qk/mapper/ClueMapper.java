package com.qk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.dto.ClueDto;
import com.qk.entity.Clue;
import com.qk.vo.OverviewVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClueMapper extends BaseMapper<Clue> {
    Clue getClueById(Integer id);

    Page<Clue> queryClueList(Page<Object> objectPage, ClueDto clueDto);

    OverviewVO getClueOverviewData();
}
