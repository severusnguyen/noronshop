package com.example.noronshopapi.controller;

import com.example.noronshopapi.service.category.CategoryService;
import com.example.noronshopcommons.data.dto.response.CategoryResponse;
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
public class CategoryController {

    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/search")
    public ResponseEntity<DfResponse<Page<CategoryResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(categoryService.search(searchRequest));
    }

    

}
