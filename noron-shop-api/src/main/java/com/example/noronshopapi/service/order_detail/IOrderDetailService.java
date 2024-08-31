package com.example.noronshopapi.service.order_detail;

import com.example.noronshopcommons.data.dto.request.OrderDetailRequest;

import java.util.List;

public interface IOrderDetailService {
    String insertMultiOrderDetailRecord(List<OrderDetailRequest> orderDetailRequests);
}
