package com.example.noronshopcommons.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopRequest {
    private String name;
    private String logo;
    private String description;
    private String shortDescription;
    private LocalDateTime createdAt;
    private Integer productQuantity;
    private Integer numFollow;
    private Integer rating;
    private Double responseRate;
    private String coverUrl;
    private String userName;
    private String email;
    private String phoneNumber;
    private String address;
    private List<BusinessItemsRequest> businessItem;
    private List<IdentityCardRequest> identityCards;
    private String status;
}
