package com.qk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResultVo {

    //"username": "zhangwuji",
//"name": "张无忌",
//"image": "https://java-ai.oss-cn-beijing.aliyuncs.com/2.png",
//"roleLabel": "user_normal",
//"token": "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MiwidXNlcm5hbWUiOiJ6aGFuZ3d1amkiLCJuYW1lIjoi5byg5peg5b-MIiwiZXhwIjoxNzQ3MzQwNDY1fQ.BVzOz0f7HUzQ-GjO_gZCyfjPQL4vcwJbSgUvZJrWPYo"
    private Integer id;
    private String username;
    private String name;
    private String image;
    private String roleLabel;
    private String token;
}
