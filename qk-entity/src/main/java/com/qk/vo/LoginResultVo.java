package com.qk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResultVo {

    private Integer id;
    private String username;
    private String name;
    private String image;
    private String roleLabel;
    private String token;
}
