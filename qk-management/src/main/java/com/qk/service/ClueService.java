package com.qk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.ClueDto;
import com.qk.dto.MarkFalseClueDto;
import com.qk.entity.Clue;

public interface ClueService extends IService<Clue> {
    Clue getClueById(Integer id);

    void trackClue(Clue clue);

    PageResult<Clue> clueList(ClueDto clueDto);

    void toBusiness(Integer id);

    void falseClue(MarkFalseClueDto markFalseClueDto, Integer id);
}
