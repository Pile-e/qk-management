package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.BusinessDto;
import com.qk.entity.Business;
import com.qk.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        log.info("新增商机,business:{}", business);
        business.setStatus(1); //待分配
        business.setCreateTime(LocalDateTime.now());
        business.setUpdateTime(LocalDateTime.now());
        businessService.save(business);
        return Result.success();
    }

    /**
     * 商机列表查询
     *
     * @param businessDto
     * @return
     */
    @GetMapping
    public Result businessList(BusinessDto businessDto) {
        log.info("商机列表查询,businessDto:{}", businessDto);
        PageResult<Business> pageResult = businessService.businessList(businessDto);
        return Result.success(pageResult);
    }
}
