package com.example.noronshopcommons.data.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionHistoryRequest {
    private Integer id;
    private String fluctuationsMoney;
    private LocalDateTime createdAt;
    private Integer shopId;
    private Integer userId;
    private String content;
    private String status;
    private String withdrawDate;
}
