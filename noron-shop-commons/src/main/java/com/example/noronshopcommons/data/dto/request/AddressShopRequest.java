package com.example.noronshopcommons.data.dto.request;

import lombok.Data;


@Data
public class AddressShopRequest {
    private Integer id;
    private String manager;
    private String phoneNumber;
    private String address;
    private String status;
    private Integer shopId;
}
