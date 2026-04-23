package com.qk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.CustomerDto;
import com.qk.entity.Customer;

public interface CustomerService extends IService<Customer> {
    PageResult<Customer> customersList(CustomerDto customerDto);
}
