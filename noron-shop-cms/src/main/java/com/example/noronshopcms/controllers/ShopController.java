package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.shop.ShopServiceImpl;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.dto.request.ShopRequest;
import com.example.noronshopcommons.data.dto.response.ShopResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms")
public class ShopController {

    ShopServiceImpl shopService;

    @Autowired
    public ShopController(ShopServiceImpl shopService) {
        this.shopService = shopService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_USER')")
    @PostMapping("/shop")
    public ResponseEntity<DfResponse<ShopResponse>> insert(
            @RequestBody(required = false) ShopRequest shopRequest
    ){
        return DfResponse
                .okEntity(shopService.insert(shopRequest));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_USER')")
    @PutMapping("/shop/{id}")
    public ResponseEntity<DfResponse<ShopResponse>> update(
            @RequestBody(required = false) ShopRequest shopRequest,
            @PathVariable("id") int id
    ){
        return DfResponse
                .okEntity(shopService.update(id, shopRequest));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/shop/{id}")
    public ResponseEntity<DfResponse<ShopResponse>> delete(
            @PathVariable("id") int id
    ){
        return DfResponse
                .okEntity(shopService.deleteById(id));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/shop/search")
    public ResponseEntity<DfResponse<Page<ShopResponse>>> search(
            @RequestBody SearchRequest searchRequest
    ){
        return DfResponse
                .okEntity(shopService.search(searchRequest));
    }

}
