package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.CustomerDto;
import com.qk.entity.Customer;
import com.qk.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * 客户列表查询
     *
     * @param customerDto
     * @return
     */
    @GetMapping
    public Result customersList(CustomerDto customerDto) {
        log.info("客户列表查询,customerDto:{}", customerDto);
        PageResult<Customer> customerPageResult = customerService.customersList(customerDto);
        return Result.success(customerPageResult);
    }
}
