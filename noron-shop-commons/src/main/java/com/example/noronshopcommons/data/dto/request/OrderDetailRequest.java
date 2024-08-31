package com.example.noronshopcommons.data.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDetailRequest {
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
}
