package com.example.noronshopcommons.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TikiProductPriceRequest {
    private Integer id;
    private Integer productId;
    private Double discount;
    private Double discountRate;
    private LocalDateTime updatedAt;
    private Double price;
}
