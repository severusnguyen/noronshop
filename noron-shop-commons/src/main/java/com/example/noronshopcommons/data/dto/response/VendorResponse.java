package com.example.noronshopcommons.data.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorResponse {
    private Integer id;
    private String handle;
    private String cover;
    private Integer ownerId;
    private Integer status;
    private String location;
    private String description;
    private String name;
    private String phone;
    private Integer shopId;
    private ShopResponse shopResponse;

}
