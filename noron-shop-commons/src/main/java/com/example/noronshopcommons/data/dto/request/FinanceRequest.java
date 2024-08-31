package com.example.noronshopcommons.data.dto.request;

import lombok.Data;


@Data
public class FinanceRequest {
    private Double totalMoneyTemporary;
    private Double totalAvailableMoney;
    private Double financeMomo;
    private Double financeMoney;
    private Double totalFee;
    private Long userId;
    private Integer shopId;

}
