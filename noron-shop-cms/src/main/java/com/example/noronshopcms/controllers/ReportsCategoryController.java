package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.reports_caterory.ReportsCategoryServiceImp;
import com.example.noronshopcommons.data.dto.request.ReportsCategoryRequest;
import com.example.noronshopcommons.data.dto.response.ReportsCategoryResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/reports-category")
public class ReportsCategoryController {

    @Autowired
    ReportsCategoryServiceImp reportsCategoryServiceImp;

    @PostMapping("")
    public ResponseEntity<DfResponse<ReportsCategoryResponse>> insert(@RequestBody ReportsCategoryRequest request){
        return DfResponse
                .okEntity(reportsCategoryServiceImp.insert(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<ReportsCategoryResponse>> update(@PathVariable int id, @RequestBody ReportsCategoryRequest request){
        return DfResponse
                .okEntity(reportsCategoryServiceImp.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<ReportsCategoryResponse>> delete(@PathVariable int id){
        return DfResponse
                .okEntity(reportsCategoryServiceImp.deleteById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<ReportsCategoryResponse>>> search(@RequestBody SearchRequest request){
        return DfResponse
                .okEntity(reportsCategoryServiceImp.searchCustom(request));
    }
    

}
