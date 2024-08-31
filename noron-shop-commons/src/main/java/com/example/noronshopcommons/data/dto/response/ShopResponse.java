package com.example.noronshopcommons.data.dto.response;

import com.example.noronshopcommons.data.dto.request.BusinessItemsRequest;
import com.example.noronshopcommons.data.dto.request.IdentityCardRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopResponse {

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
