package com.example.noronshopapi.controller;

import com.example.noronshopapi.service.category_shop.CategoryShopService;
import com.example.noronshopcommons.data.dto.response.CategoryShopResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryShopController {

    private CategoryShopService categoryShopService;

    @Autowired
    public CategoryShopController(CategoryShopService categoryShopService) {
        this.categoryShopService = categoryShopService;
    }

    @GetMapping("/category-shop/search")
    public ResponseEntity<DfResponse<Page<CategoryShopResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(categoryShopService.search(searchRequest));
    }


}
