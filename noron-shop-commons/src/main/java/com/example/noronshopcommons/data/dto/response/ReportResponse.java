package com.example.noronshopcommons.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private Integer id;
    private String name;
    private String status;
    private String active;
    private String userNameShop;
    private Integer reportsCategoryId;
    private ReportsCategoryResponse reportsCategoryResponse;
}
