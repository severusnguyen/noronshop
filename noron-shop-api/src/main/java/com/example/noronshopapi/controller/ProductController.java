package com.example.noronshopapi.controller;

import com.example.noronshopapi.service.product.ProductService;
import com.example.noronshopcommons.data.dto.response.ProductResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/search")
    public ResponseEntity<DfResponse<Page<ProductResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(productService.search(searchRequest));
    }

    @GetMapping("/product/detail/{id}")
    public ResponseEntity<DfResponse<ProductResponse>> findById(@PathVariable("id") int id){
        return DfResponse
                .okEntity(productService.findById(id));
    }
}
