package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.banner.BannerServiceImp;
import com.example.noronshopcommons.data.dto.request.BannerRequest;
import com.example.noronshopcommons.data.dto.request.BannerResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class BannerController {

    @Autowired
    BannerServiceImp bannerServiceImp;

    @PostMapping("/cms/banner")
    public ResponseEntity<DfResponse<BannerResponse>> insert(@RequestBody BannerRequest bannerRequest){
        return DfResponse
                .okEntity(bannerServiceImp.insert(bannerRequest));
    }

    @PutMapping("/cms/banner/{id}")
    public ResponseEntity<DfResponse<BannerResponse>> update(@PathVariable int id, @RequestBody BannerRequest bannerRequest){
        return DfResponse
                .okEntity(bannerServiceImp.update(id, bannerRequest));
    }

    @DeleteMapping("/cms/banner/{id}")
    public ResponseEntity<DfResponse<BannerResponse>> delete(@PathVariable int id){
        return DfResponse
                .okEntity(bannerServiceImp.deleteById(id));
    }

    @PostMapping("/cms/banner/search")
    public ResponseEntity<DfResponse<Page<BannerResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(bannerServiceImp.search(searchRequest));
    }
}
