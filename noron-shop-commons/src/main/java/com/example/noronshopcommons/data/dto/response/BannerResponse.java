package com.example.noronshopcommons.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerResponse {
    private Integer id;
    private String title;
    private String description;
    private String type;
    private String coverUrl;
    private String name;
    private String logo;
    private String images;
    private String active;
}
