package com.qk.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.dto.CustomerDto;
import com.qk.entity.Customer;
import com.qk.mapper.CustomerMapper;
import com.qk.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public PageResult<Customer> customersList(CustomerDto customerDto) {
        Page<Customer> page = customerMapper.customersList(new Page<>(customerDto.getPage(), customerDto.getPageSize()), customerDto);
        return new PageResult<>(page.getTotal(), page.getRecords());
    }
}
