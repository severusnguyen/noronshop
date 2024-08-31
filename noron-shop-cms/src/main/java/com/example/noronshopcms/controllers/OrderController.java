package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.order.OrderServiceImp;
import com.example.noronshopcommons.data.dto.request.OrderRequest;
import com.example.noronshopcommons.data.dto.response.OrderResponse;
import com.example.noronshopcommons.data.dto.response.ShopResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/order")
public class OrderController {

    @Autowired
    OrderServiceImp orderServiceImp;

    @PostMapping("")
    public ResponseEntity<DfResponse<OrderResponse>> insert(@RequestBody OrderRequest orderRequest){
        return DfResponse
                .okEntity(orderServiceImp.insert(orderRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<OrderResponse>> update(@PathVariable int id, @RequestBody OrderRequest orderRequest){
        return DfResponse
                .okEntity(orderServiceImp.update(id, orderRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<OrderResponse>> delete(@PathVariable int id){
        return DfResponse
                .okEntity(orderServiceImp.deleteById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<OrderResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(orderServiceImp.search(searchRequest));
    }

}
