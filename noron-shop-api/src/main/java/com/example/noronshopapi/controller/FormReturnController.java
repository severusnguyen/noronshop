package com.example.noronshopapi.controller;

import com.example.noronshopapi.service.form_return.FormReturnService;
import com.example.noronshopcommons.data.dto.request.FormReturnRequest;
import com.example.noronshopcommons.data.dto.request.OrderRequest;
import com.example.noronshopcommons.data.dto.response.FormReturnResponse;
import com.example.noronshopcommons.data.dto.response.OrderResponse;
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
@RequestMapping("/api/form-return")
public class FormReturnController {

    @Autowired
    private FormReturnService formReturnService;

    @PostMapping("/add")
    public ResponseEntity<DfResponse<FormReturnResponse>> add(@RequestBody FormReturnRequest formReturnRequest) {
        return DfResponse.okEntity(formReturnService.insert(formReturnRequest));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<FormReturnResponse>>> search(@RequestBody SearchRequest searchRequest) {
        return DfResponse.okEntity(formReturnService.search(searchRequest));
    }
}
