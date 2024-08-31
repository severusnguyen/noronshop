package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.category_shop.CategoryShopService;
import com.example.noronshopcommons.data.dto.response.CategoryShopResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.dto.request.CategoryShopRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms")
public class CategoryShopController {

    private CategoryShopService categoryShopService;

    @Autowired
    public CategoryShopController(CategoryShopService categoryShopService) {
        this.categoryShopService = categoryShopService;
    }

    @PostMapping("/category-shop")
    public ResponseEntity<DfResponse<CategoryShopResponse>> insert(
            @RequestBody CategoryShopRequest categoryShopRequest
    ){
        return DfResponse
                .okEntity(categoryShopService.insert(categoryShopRequest));
    }

    @PutMapping("/category-shop/{id}")
    public ResponseEntity<DfResponse<CategoryShopResponse>> update(
            @RequestBody CategoryShopRequest categoryShopRequest,
            @PathVariable(value = "id") int id
    ){
        return DfResponse
                .okEntity(categoryShopService.update(id, categoryShopRequest));
    }

    @DeleteMapping("/category-shop/{id}")
    public ResponseEntity<DfResponse<CategoryShopResponse>> delete(
            @PathVariable(value = "id") int id
    ){
        return DfResponse
                .okEntity(categoryShopService.deleteById(id));
    }

    @PostMapping("/category-shop/search")
    public ResponseEntity<DfResponse<Page<CategoryShopResponse>>> search(
            @RequestBody SearchRequest searchRequest
    ){
        return DfResponse
                .okEntity(categoryShopService.search(searchRequest));
    }

}
