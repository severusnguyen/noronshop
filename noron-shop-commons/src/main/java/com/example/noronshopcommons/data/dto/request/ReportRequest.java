package com.example.noronshopcommons.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequest {
    private Integer id;
    private String name;
    private String status;
    private String active;
    private String userNameShop;
    private Integer reportsCategoryId;
}
