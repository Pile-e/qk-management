package com.qk.controller;

import com.qk.common.Result;
import com.qk.entity.Business;
import com.qk.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/businesses")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    /**
     * 新增商机
     *
     * @param business
     * @return
     */
    @PostMapping
    public Result addBusiness(@RequestBody Business business) {
        business.setStatus(1); //待分配
        business.setCreateTime(LocalDateTime.now());
        business.setUpdateTime(LocalDateTime.now());
        businessService.save(business);
        return Result.success();
    }
}
