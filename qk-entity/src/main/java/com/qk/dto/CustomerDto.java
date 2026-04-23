package com.qk.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private String phone;
    private String name;
    private Integer channel;
    private Integer subject;
    private Integer page;
    private Integer pageSize;
}
