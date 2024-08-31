package com.example.noronshopapi.controller;

import com.example.noronshopapi.service.review.ReviewServiceImpl;
import com.example.noronshopapi.service.sub_review.SubReviewServiceImpl;
import com.example.noronshopcommons.data.dto.request.ReviewRequest;
import com.example.noronshopcommons.data.dto.request.SubReviewRequest;
import com.example.noronshopcommons.data.dto.response.ReviewResponse;
import com.example.noronshopcommons.data.dto.response.SubReviewResponse;
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
@RequestMapping("/api/sub-review")
public class SubReviewController {

    @Autowired
    private SubReviewServiceImpl subReviewService;

    @PostMapping("/add")
    public ResponseEntity<DfResponse<SubReviewResponse>> add(@RequestBody SubReviewRequest subReviewRequest) {
        return DfResponse.okEntity(subReviewService.insert(subReviewRequest));
    }

    @PostMapping("/update")
    public ResponseEntity<DfResponse<SubReviewResponse>> update(@RequestBody SubReviewRequest subReviewRequest) {
        return DfResponse.okEntity(subReviewService.update( subReviewRequest.getId(),subReviewRequest));
    }

    @PostMapping("/delete")
    public ResponseEntity<DfResponse<SubReviewResponse>> delete(@RequestBody SubReviewRequest subReviewRequest) {
        return DfResponse.okEntity(subReviewService.delete(subReviewRequest.getId()));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<SubReviewResponse>>> search(@RequestBody SearchRequest searchRequest) {
        return DfResponse.okEntity(subReviewService.search(searchRequest));
    }
}
