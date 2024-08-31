package com.example.noronshopcommons.data.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShippingAddressRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String city;
    private String districts;
    private String wards;
    private LocalDateTime createdAt;
    private Long userId;
    private String address;
    private Integer id;
}
