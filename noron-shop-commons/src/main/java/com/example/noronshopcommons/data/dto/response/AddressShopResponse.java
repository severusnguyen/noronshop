package com.example.noronshopcommons.data.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressShopResponse {
    private Integer id;
    private String manager;
    private String phoneNumber;
    private String address;
    private String status;
    private Integer shopId;
}
