package com.qk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDto {
    private Integer businessId;
    private String name;
    private String phone;
    private Integer status;
    private String assignName;
    private Integer page;
    private Integer pageSize;
}
