package com.example.noronshopcommons.data.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FinanceDetailResponse {
    private LocalDateTime dateTime;
    private Double totalMoney;
    private Double transportFee;
    private Double discountNoron;
    private Double discountShop;
    private Double transactionFee;
    private Double finance;
    private OrderResponse order;
    private UserResponse user;
    private Double totalMoneyOrder;
}
