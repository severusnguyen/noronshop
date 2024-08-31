package com.example.noronshopapi.controller;

import com.example.noronshopapi.service.review.ReviewServiceImpl;
import com.example.noronshopcommons.data.dto.request.CartRequest;
import com.example.noronshopcommons.data.dto.request.CategoryRequest;
import com.example.noronshopcommons.data.dto.request.ReviewRequest;
import com.example.noronshopcommons.data.dto.response.CartResponse;
import com.example.noronshopcommons.data.dto.response.CategoryResponse;
import com.example.noronshopcommons.data.dto.response.CategoryWithChildrenResponse;
import com.example.noronshopcommons.data.dto.response.ReviewResponse;
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
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewServiceImpl reviewService;

    @PostMapping("/add")
    public ResponseEntity<DfResponse<ReviewResponse>> add(@RequestBody ReviewRequest reviewRequest) {
        return DfResponse.okEntity(reviewService.insert(reviewRequest));
    }

    @PostMapping("/update")
    public ResponseEntity<DfResponse<ReviewResponse>> update(@RequestBody ReviewRequest reviewRequest) {
        return DfResponse.okEntity(reviewService.update( reviewRequest.getId(),reviewRequest));
    }

    @PostMapping("/delete")
    public ResponseEntity<DfResponse<ReviewResponse>> delete(@RequestBody ReviewRequest reviewRequest) {
        return DfResponse.okEntity(reviewService.delete(reviewRequest.getId()));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<ReviewResponse>>> search(@RequestBody SearchRequest searchRequest) {
        return DfResponse.okEntity(reviewService.search(searchRequest));
    }
}
