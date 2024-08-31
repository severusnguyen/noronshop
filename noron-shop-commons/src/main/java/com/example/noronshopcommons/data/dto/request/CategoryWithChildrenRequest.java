package com.example.noronshopcommons.data.dto.request;

import com.example.noronshopcommons.data.dto.response.CategoryResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class CategoryWithChildrenRequest {
    private Integer id;
    private String name;
    private String imageSrc;
    private String imageTitle;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Long createdBy;
    private String parentSlug;
    private String description;
    private String handle;
    private Integer order;
    private List<CategoryResponse> children;
}
