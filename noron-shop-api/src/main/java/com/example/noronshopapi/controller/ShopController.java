package com.example.noronshopapi.controller;

import com.example.noronshopapi.service.shop.ShopService;
import com.example.noronshopcommons.data.dto.response.ShopResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ShopController {

    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shop/search")
    public ResponseEntity<DfResponse<Page<ShopResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(shopService.search(searchRequest));
    }
}
