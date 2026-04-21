package com.qk.controller;

import com.qk.common.Result;
import com.qk.entity.Clue;
import com.qk.service.ClueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 分配线索
     *
     * @param clueId
     * @param userId
     * @return
     */
    @PutMapping("/clues/assign/{clueId}/{userId}")
    public Result assignClue(@PathVariable Integer clueId, @PathVariable Integer userId) {
        log.info("分配线索,clueId:{},userId:{}", clueId, userId);
        //查询线索是否存在
        Clue clue = new Clue();
        clue.setId(clueId);
        clue.setUserId(userId); //分配给指定用户
        clue.setStatus(1); //待跟进
        clue.setUpdateTime(LocalDateTime.now()); //更新时间

        //保存更新后的信息
        clueService.updateById(clue);
        return Result.success();
    }

    /**
     * 根据id查询线索
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getClueById(@PathVariable Integer id) {
        Clue clue = clueService.getClueById(id);
        return Result.success(clue);
    }
}
