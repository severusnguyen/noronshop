package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.report.ReportServiceImp;
import com.example.noronshopcommons.data.dto.request.ReportRequest;
import com.example.noronshopcommons.data.dto.response.ReportResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/report")
public class ReportController {

    @Autowired
    ReportServiceImp reportServiceImp;

    @PostMapping("")
    public ResponseEntity<DfResponse<ReportResponse>> insert(@RequestBody ReportRequest request){
        return DfResponse
                .okEntity(reportServiceImp.insert(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<ReportResponse>> update(@PathVariable int id, @RequestBody ReportRequest request){
        return DfResponse
                .okEntity(reportServiceImp.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<ReportResponse>> delete(@PathVariable int id){
        return DfResponse
                .okEntity(reportServiceImp.deleteById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<ReportResponse>>> search(@RequestBody SearchRequest request){
        return DfResponse
                .okEntity(reportServiceImp.searchCustom(request));
    }
}
