package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.product.ProductCmsService;
import com.example.noronshopcommons.data.dto.request.ProductRequest;
import com.example.noronshopcommons.data.dto.response.ProductResponse;
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
@RequestMapping("/cms/product")
public class ProductCmsController {

    @Autowired
    private ProductCmsService productCmsService;

    @PostMapping("/insert")
    public ResponseEntity<DfResponse<ProductResponse>> insert(@RequestBody ProductRequest productRequest) {
        return DfResponse.okEntity(productCmsService.insert(productRequest));
    }

    @PostMapping("/update")
    public ResponseEntity<DfResponse<ProductResponse>> update(@RequestBody ProductRequest productRequest) {
        return DfResponse.okEntity(productCmsService.update( productRequest.getId(),productRequest));
    }

    @PostMapping("/delete")
    public ResponseEntity<DfResponse<ProductResponse>> delete(@RequestBody ProductRequest productRequest) {
        return DfResponse.okEntity(productCmsService.deleteById(productRequest.getId()));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<ProductResponse>>> search(@RequestBody SearchRequest searchRequest) {
        return DfResponse.okEntity(productCmsService.searchCustomRes(searchRequest));
    }
}
