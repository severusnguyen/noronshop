package com.example.noronshopapi.controller;

import com.example.noronshopapi.service.order.OrderService;
import com.example.noronshopapi.service.product.ProductService;
import com.example.noronshopcommons.data.dto.request.OrderRequest;
import com.example.noronshopcommons.data.dto.request.ReviewRequest;
import com.example.noronshopcommons.data.dto.response.OrderResponse;
import com.example.noronshopcommons.data.dto.response.ProductResponse;
import com.example.noronshopcommons.data.dto.response.ReviewResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/findOrder")
    public ResponseEntity<DfResponse<Page<OrderResponse>>> findOrderByUserId(@RequestBody OrderRequest orderRequest){
        return DfResponse.okEntity(orderService.findOrderByUserId(orderRequest.getUserId()));
    }

    @PostMapping("/add")
    public ResponseEntity<DfResponse<OrderResponse>> add(@RequestBody OrderRequest orderRequest) {
        return DfResponse.okEntity(orderService.insert(orderRequest));
    }

    @PostMapping("/cancel")
    public ResponseEntity<DfResponse<OrderResponse>> cancel(@RequestBody OrderRequest orderRequest) {
        return DfResponse.okEntity(orderService.delete(orderRequest.getId()));
    }

}
