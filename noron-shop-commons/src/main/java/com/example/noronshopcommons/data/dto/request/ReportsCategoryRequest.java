package com.example.noronshopcommons.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportsCategoryRequest {
    private Integer id;
    private String nameCategory;
    private String description;
    private Integer shopId;
}
