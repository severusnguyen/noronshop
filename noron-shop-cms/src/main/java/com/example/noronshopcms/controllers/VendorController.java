package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.vendor.VendorServiceImp;
import com.example.noronshopcommons.data.dto.request.VendorRequest;
import com.example.noronshopcommons.data.dto.response.VendorResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/vendor")
public class VendorController {

    @Autowired
    VendorServiceImp vendorServiceImp;

    @PostMapping("")
    public ResponseEntity<DfResponse<VendorResponse>> insert(@RequestBody VendorRequest vendorRequest){
        return DfResponse
                .okEntity(vendorServiceImp.insert(vendorRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<VendorResponse>> update(@PathVariable int id, @RequestBody VendorRequest vendorRequest){
        return DfResponse
                .okEntity(vendorServiceImp.update(id, vendorRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<VendorResponse>> delete(@PathVariable int id){
        return DfResponse
                .okEntity(vendorServiceImp.deleteById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<VendorResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(vendorServiceImp.searchCustom(searchRequest));
    }
}
