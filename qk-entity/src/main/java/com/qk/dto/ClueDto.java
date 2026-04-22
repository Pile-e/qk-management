package com.qk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClueDto {
    //clues?clueId=55&phone=13309091233&status=1&channel=1&assignName=张三&page=1&pageSize=5
    private Integer clueId;
    private String phone;
    private Integer status;
    private Integer channel;
    private String assignName;
    private Integer page;
    private Integer pageSize;
}
