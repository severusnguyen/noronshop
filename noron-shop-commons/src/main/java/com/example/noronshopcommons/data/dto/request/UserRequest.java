package com.example.noronshopcommons.data.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String address;
    private String avatar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer status;
}
