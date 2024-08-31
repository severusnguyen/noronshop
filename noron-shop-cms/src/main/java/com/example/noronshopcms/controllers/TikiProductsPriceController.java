package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.tiki_products_price.TikiProductPriceServiceImp;
import com.example.noronshopcommons.data.dto.request.TikiProductPriceRequest;
import com.example.noronshopcommons.data.dto.response.TikiProductPriceResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/tiki-product-price")
public class TikiProductsPriceController {

    @Autowired
    TikiProductPriceServiceImp tikiProductPriceServiceImp;

    @PostMapping("")
    public ResponseEntity<DfResponse<TikiProductPriceResponse>> insert(@RequestBody TikiProductPriceRequest request){
        return DfResponse
                .okEntity(tikiProductPriceServiceImp.insert(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<TikiProductPriceResponse>> update(@PathVariable int id, @RequestBody TikiProductPriceRequest request){
        return DfResponse
                .okEntity(tikiProductPriceServiceImp.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<TikiProductPriceResponse>> delete(@PathVariable int id){
        return DfResponse
                .okEntity(tikiProductPriceServiceImp.deleteById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<TikiProductPriceResponse>>> search(@RequestBody SearchRequest request){
        return DfResponse
                .okEntity(tikiProductPriceServiceImp.searchCustom(request));
    }
}
