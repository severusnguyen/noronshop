package com.example.noronshopcommons.data.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryShopResponse{
    private Integer id;
    private String name;
    private String handle;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Long createdBy;
    private Integer order;
    private ShopResponse shopResponse;
    private Integer parentId;
    private int quantity;
}
