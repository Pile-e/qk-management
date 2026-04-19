package com.qk.controller;

import com.qk.common.Result;
import com.qk.entity.Clue;
import com.qk.service.ClueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/clues")
public class ClueController {
    @Autowired
    private ClueService clueService;

    /**
     * 新增线索
     *
     * @param clue
     * @return
     */
    @PostMapping
    private Result addClue(@RequestBody Clue clue) {
        clue.setCreateTime(LocalDateTime.now());
        clue.setUpdateTime(LocalDateTime.now());
        clue.setStatus(1);
        clueService.save(clue);
        return Result.success();
    }
}
