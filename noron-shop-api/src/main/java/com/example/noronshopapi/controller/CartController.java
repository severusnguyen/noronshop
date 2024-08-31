package com.example.noronshopapi.controller;

import com.example.noronshopapi.service.cart.CartServiceImpl;
import com.example.noronshopcommons.data.dto.request.CartRequest;
import com.example.noronshopcommons.data.dto.response.CartResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @PostMapping("/init")
    public ResponseEntity<DfResponse<CartResponse>> init(@RequestBody CartRequest categoryRequest) {
        return DfResponse.okEntity(cartService.initCart(categoryRequest));
    }

    @PostMapping("/change-product")
    public ResponseEntity<DfResponse<CartResponse>> changeProduct(@RequestBody CartRequest categoryRequest) {
        return DfResponse.okEntity(cartService.update(categoryRequest.getId(), categoryRequest));
    }

    @PostMapping("/getCart")
    public ResponseEntity<DfResponse<CartResponse>> getCart(@RequestBody CartRequest categoryRequest) {
        return DfResponse.okEntity(cartService.getCart(categoryRequest));
    }

}
