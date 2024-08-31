package com.example.noronshopcommons.data.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FinanceDetailRequest {
    private LocalDateTime dateTime;
    private Double totalMoney;
    private Double transportFee;
    private Double discountNoron;
    private Double discountShop;
    private Double transactionFee;
    private Double finance;
    private Integer orderId;
    private Long userId;
    private Double totalMoneyOrder;
}
