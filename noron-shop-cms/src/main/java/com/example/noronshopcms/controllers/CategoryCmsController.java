package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.CategoryCmsService;
import com.example.noronshopcms.service.CategoryWithChildrenCmsService;
import com.example.noronshopcommons.data.dto.request.CategoryRequest;
import com.example.noronshopcommons.data.dto.response.CategoryResponse;
import com.example.noronshopcommons.data.dto.response.CategoryWithChildrenResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopconfig.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/category")
public class CategoryCmsController {

    @Autowired
    private CategoryCmsService categoryCmsService;

    @Autowired
    private CategoryWithChildrenCmsService categoryWithChildrenCmsService;

    @PostMapping("/insert")
    public ResponseEntity<DfResponse<CategoryResponse>> insert(@RequestBody CategoryRequest categoryRequest) {
        return DfResponse
                .okEntity(categoryCmsService.insert(categoryRequest));
    }

    @PostMapping("/update")
    public ResponseEntity<DfResponse<CategoryResponse>> update(@RequestBody CategoryRequest categoryRequest) {
        return DfResponse
                .okEntity(categoryCmsService.update( categoryRequest.getId(),categoryRequest));
    }

    @PostMapping("/delete")
    public ResponseEntity<DfResponse<CategoryResponse>> delete(@RequestBody CategoryRequest categoryRequest) {
        return DfResponse
                .okEntity(categoryCmsService.deleteById(categoryRequest.getId()));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<CategoryWithChildrenResponse>>> search(@RequestBody SearchRequest searchRequest) {
        return DfResponse
                .okEntity(categoryWithChildrenCmsService.search(searchRequest));
    }
}
