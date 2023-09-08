package com.example.hotelmanagesystem.controller.dto;
import lombok.Data;

/**
 * 接受前端登录请求的参数
 */
@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String token;
    private String role;
}
