package com.example.noronshopapi.controller;

import com.example.noronshopapi.service.shipping_address.ShippingAddressService;
import com.example.noronshopcommons.data.dto.request.ShippingAddressRequest;
import com.example.noronshopcommons.data.dto.response.ShippingAddressResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shipping-address")
public class ShippingAddressController {
    @Autowired
    private ShippingAddressService shippingAddressService;

    @PostMapping("/add")
    public ResponseEntity<DfResponse<ShippingAddressResponse>> add(@RequestBody ShippingAddressRequest shippingAddressRequest) {
        return DfResponse.okEntity(shippingAddressService.insert(shippingAddressRequest));
    }

    @PostMapping("/update")
    public ResponseEntity<DfResponse<ShippingAddressResponse>> update(@RequestBody ShippingAddressRequest shippingAddressRequest) {
        return DfResponse.okEntity(shippingAddressService.update( shippingAddressRequest.getId(),shippingAddressRequest));
    }

    @PostMapping("/delete")
    public ResponseEntity<DfResponse<ShippingAddressResponse>> delete(@RequestBody ShippingAddressRequest shippingAddressRequest) {
        return DfResponse.okEntity(shippingAddressService.delete(shippingAddressRequest.getId()));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<ShippingAddressResponse>>> search(@RequestBody SearchRequest searchRequest) {
        return DfResponse.okEntity(shippingAddressService.search(searchRequest));
    }
}
