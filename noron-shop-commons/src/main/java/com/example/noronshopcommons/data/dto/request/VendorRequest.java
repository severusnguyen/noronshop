package com.example.noronshopcommons.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorRequest {
    private Integer id;
    private String handle;
    private String cover;
    private int ownerId;
    private int status;
    private String location;
    private String description;
    private String name;
    private String phone;
    private int shopId;
}
