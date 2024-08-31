package com.example.noronshopcommons.data.dto.response;

import lombok.Data;


@Data
public class FinanceResponse {
    private Double totalMoneyTemporary;
    private Double totalAvailableMoney;
    private Double financeMomo;
    private Double financeMoney;
    private Double totalFee;
    private Long userId;
    private ShopResponse shop;
}
