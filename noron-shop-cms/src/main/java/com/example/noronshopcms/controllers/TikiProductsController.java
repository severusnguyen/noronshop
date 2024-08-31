package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.tiki_products.TikiProductsServiceImp;
import com.example.noronshopcommons.data.dto.request.TikiProductsRequest;
import com.example.noronshopcommons.data.dto.response.TikiProductsResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/tiki-products")
public class TikiProductsController {

    @Autowired
    TikiProductsServiceImp tikiProductsServiceImp;

    @PostMapping("")
    public ResponseEntity<DfResponse<TikiProductsResponse>> insert(@RequestBody TikiProductsRequest request){
        return DfResponse
                .okEntity(tikiProductsServiceImp.insert(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<TikiProductsResponse>> update(@PathVariable int id, @RequestBody TikiProductsRequest request){
        return DfResponse
                .okEntity(tikiProductsServiceImp.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<TikiProductsResponse>> delete(@PathVariable int id){
        return DfResponse
                .okEntity(tikiProductsServiceImp.deleteById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<TikiProductsResponse>>> search(@RequestBody SearchRequest request){
        return DfResponse
                .okEntity(tikiProductsServiceImp.searchCustom(request));
    }
}
