package com.example.noronshopcommons.data.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CategoryRequest {
    private Integer id;
    private String name;
    private Integer parentId;
    private String imageSrc;
    private String imageTitle;
    private LocalDateTime deletedAt;
    private Long createdBy;
    private String parentSlug;
    private String description;
    private String handle;
    private Integer order;
}
