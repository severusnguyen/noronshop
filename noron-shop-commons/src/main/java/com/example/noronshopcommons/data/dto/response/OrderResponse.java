package com.example.noronshopcommons.data.dto.response;

import com.example.noronshopcommons.data.dto.request.ShippingRequest;
import com.example.noronshopcommons.data.modal.OrderShipping;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    private int id;
    private String status;
    private int tracking;
    private int totalAmount;
    private int totalCoin;
    private int totalDiscount;
    private String currencyCode;
    private int userId;
    private String paymentMethod;
    private List<OrderShipping> shippingAddress;
    private String code;
    private int transactionId;
    private int financeDetailId;
    private int customerPaidInfoId;
    private int shopId;
    private boolean isPaid;
    private String statusShip;
    private List<OrderDetailResponse> orderDetails;
}
