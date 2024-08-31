package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.categoryshop_product.CateShopProductService;
import com.example.noronshopcommons.data.dto.request.CateShopProductRequest;
import com.example.noronshopcommons.data.dto.response.CateShopProductResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms")
public class CateShopProductController {


    CateShopProductService cateShopProductService;

    @Autowired
    public CateShopProductController(CateShopProductService cateShopProductService) {
        this.cateShopProductService = cateShopProductService;
    }

    @PostMapping("/categoryshop-product")
    public ResponseEntity<DfResponse<CateShopProductResponse>> insert(@RequestBody CateShopProductRequest cateShopProductRequest){
        return DfResponse
                .okEntity(cateShopProductService.insert(cateShopProductRequest));
    }

    @PutMapping("/categoryshop-product/{id}")
    public ResponseEntity<DfResponse<CateShopProductResponse>> update(@RequestBody CateShopProductRequest cateShopProductRequest, @PathVariable("id") int id){
        return DfResponse
                .okEntity(cateShopProductService.update(id, cateShopProductRequest));
    }

    @DeleteMapping("/categoryshop-product/{id}")
    public ResponseEntity<DfResponse<CateShopProductResponse>> delete(@PathVariable("id") int id){
        return DfResponse
                .okEntity(cateShopProductService.deleteById(id));
    }

    @PostMapping("/categoryshop-product/search")
    public ResponseEntity<DfResponse<Page<CateShopProductResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(cateShopProductService.search(searchRequest));
    }

}
