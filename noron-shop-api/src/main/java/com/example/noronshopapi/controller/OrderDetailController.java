package com.example.noronshopapi.controller;

import com.example.noronshopapi.service.order_detail.OrderDetailService;
import com.example.noronshopcommons.data.dto.request.OrderDetailRequest;
import com.example.noronshopcommons.data.dto.request.ReviewRequest;
import com.example.noronshopcommons.data.dto.response.OrderDetailResponse;
import com.example.noronshopcommons.data.dto.response.ReviewResponse;
import com.example.noronshopcommons.data.dto.response.SuccessMessage;
import com.example.noronshopcommons.data.modal.DfResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-detail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/add")
    public ResponseEntity<DfResponse<String>> add(@RequestBody List<OrderDetailRequest> orderDetailRequest) {
        return DfResponse.okEntity(orderDetailService.insertMultiOrderDetailRecord(orderDetailRequest));
    }

}
