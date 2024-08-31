package com.example.noronshopcommons.data.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryShopRequest {
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
    private Integer shopId;
    private Integer parentId;
}
