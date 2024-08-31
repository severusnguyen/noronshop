package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.address_shop.AddressShopService;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.request.AddressShopRequest;
import com.example.noronshopcommons.data.dto.response.AddressShopResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms")
public class AddressShopController {

    private AddressShopService addressShopService;

    @Autowired
    public AddressShopController(AddressShopService addressShopService) {
        this.addressShopService = addressShopService;
    }

    @PostMapping("/address-shop")
    public ResponseEntity<DfResponse<AddressShopResponse>> insert(@RequestBody AddressShopRequest addressShopRequest){
        return DfResponse
                .okEntity(addressShopService.insert(addressShopRequest));
    }

    @PutMapping("/address-shop/{id}")
    public ResponseEntity<DfResponse<AddressShopResponse>> update(@RequestBody AddressShopRequest addressShopRequest, @PathVariable("id") int id){
        return DfResponse
                .okEntity(addressShopService.update(id, addressShopRequest));
    }

    @DeleteMapping("/address-shop/{id}")
    public ResponseEntity<DfResponse<AddressShopResponse>> delete(@PathVariable("id") int id){
        return DfResponse
                .okEntity(addressShopService.deleteById(id));
    }

    @PostMapping("/address-shop/search")
    public ResponseEntity<DfResponse<Page<AddressShopResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(addressShopService.search(searchRequest));
    }

}
