package com.example.noronshopcommons.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TikiProductPriceResponse {
    private Integer id;
    private Integer productId;
    private ProductResponse productResponse;
    private Double discount;
    private Double discountRate;
    private LocalDateTime updatedAt;
    private Double price;
}
