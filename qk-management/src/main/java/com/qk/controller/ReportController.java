package com.qk.controller;

import com.qk.common.Result;
import com.qk.service.ReportService;
import com.qk.vo.OverviewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/report/overview")
    public Result report() {
        OverviewVO overviewVO = reportService.report();
        return Result.success(overviewVO);
    }
}
