package com.example.noronshopcommons.data.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionHistoryResponse {
    private Integer id;
    private String fluctuationsMoney;
    private LocalDateTime createdAt;
    private ShopResponse shop;
    private UserResponse user;
    private String content;
    private String status;
    private String withdrawDate;
}
