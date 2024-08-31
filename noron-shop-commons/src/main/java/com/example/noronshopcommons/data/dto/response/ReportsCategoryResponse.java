package com.example.noronshopcommons.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportsCategoryResponse {
    private Integer id;
    private String nameCategory;
    private String description;
    private Integer shopId;
    private ShopResponse shopResponse;
}
