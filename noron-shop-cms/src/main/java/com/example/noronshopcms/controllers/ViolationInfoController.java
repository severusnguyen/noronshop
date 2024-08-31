package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.violation_info.ViolationInfoServiceImp;
import com.example.noronshopcommons.data.dto.request.ViolationInfoRequest;
import com.example.noronshopcommons.data.dto.response.ViolationInfoResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/violation-info")
public class ViolationInfoController {

    @Autowired
    ViolationInfoServiceImp serviceImp;

    @PostMapping("")
    public ResponseEntity<DfResponse<ViolationInfoResponse>> insert(@RequestBody ViolationInfoRequest request){
        return DfResponse
                .okEntity(serviceImp.insert(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<ViolationInfoResponse>> update(@PathVariable int id, @RequestBody ViolationInfoRequest request){
        return DfResponse
                .okEntity(serviceImp.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<ViolationInfoResponse>> delete(@PathVariable int id){
        return DfResponse
                .okEntity(serviceImp.deleteById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<ViolationInfoResponse>>> search(@RequestBody SearchRequest request){
        return DfResponse
                .okEntity(serviceImp.searchCustom(request));
    }
}
