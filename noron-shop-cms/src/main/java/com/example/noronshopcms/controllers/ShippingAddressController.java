package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.shipping_address.ShippingAddressServiceImp;
import com.example.noronshopcommons.data.dto.request.ShippingAddressRequest;
import com.example.noronshopcommons.data.dto.response.ShippingAddressResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/shipping-address")
public class ShippingAddressController {

    @Autowired
    ShippingAddressServiceImp shippingAddressServiceImp;

    @PostMapping("")
    public ResponseEntity<DfResponse<ShippingAddressResponse>> insert(@RequestBody ShippingAddressRequest request){
        return DfResponse
                .okEntity(shippingAddressServiceImp.insert(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<ShippingAddressResponse>> update(@RequestBody int id, @RequestBody ShippingAddressRequest request){
        return DfResponse
                .okEntity(shippingAddressServiceImp.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<ShippingAddressResponse>> delete(@RequestBody int id){
        return DfResponse
                .okEntity(shippingAddressServiceImp.deleteById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<ShippingAddressResponse>>> search(@RequestBody SearchRequest request){
        return DfResponse
                .okEntity(shippingAddressServiceImp.searchCustom(request));
    }
}
