package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.comment.CommentServiceImp;
import com.example.noronshopcommons.data.dto.request.CommentRequest;
import com.example.noronshopcommons.data.dto.response.CommentResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/comment")
public class CommentController {

    @Autowired
    CommentServiceImp commentServiceImp;

    @PostMapping("")
    public ResponseEntity<DfResponse<CommentResponse>> insert(@RequestBody CommentRequest request){
        return DfResponse
                .okEntity(commentServiceImp.insert(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<CommentResponse>> update(@PathVariable int id, @RequestBody CommentRequest request){
        return DfResponse
                .okEntity(commentServiceImp.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<CommentResponse>> delete(@PathVariable int id){
        return DfResponse
                .okEntity(commentServiceImp.deleteById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<CommentResponse>>> search(@RequestBody SearchRequest request){
        return DfResponse
                .okEntity(commentServiceImp.searchCustom(request));
    }
}
