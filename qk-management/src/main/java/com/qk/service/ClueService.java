package com.qk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.entity.Clue;

public interface ClueService extends IService<Clue> {
    Clue getClueById(Integer id);

    void trackClue(Clue clue);
}
