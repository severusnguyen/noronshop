package com.example.noronshopcommons.data.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailResponse {
    private Integer id;
    private Integer productId;
    private Integer orderId;
    private Integer quantity;
    private Double amount;
    private Double coin;
    private Double tax;
    private String currencyCode;
    private LocalDateTime createdAt;
    private String checkReview;
    private Integer shopId;
    private ProductResponse product;
}
