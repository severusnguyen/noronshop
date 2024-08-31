package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.finance_details.FinanceDetailService;
import com.example.noronshopcommons.data.dto.request.FinanceDetailRequest;
import com.example.noronshopcommons.data.dto.response.FinanceDetailResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms")
public class FinanceDetailController {

    FinanceDetailService financeDetailService;

    @Autowired
    public FinanceDetailController(FinanceDetailService financeDetailService) {
        this.financeDetailService = financeDetailService;
    }

    @PostMapping("/finance-detail")
    public ResponseEntity<DfResponse<FinanceDetailResponse>> insert(@RequestBody FinanceDetailRequest financeDetailRequest){
        return DfResponse
                .okEntity(financeDetailService.insert(financeDetailRequest));
    }

    @PutMapping("/finance-detail/{id}")
    public ResponseEntity<DfResponse<FinanceDetailResponse>> update(@RequestBody FinanceDetailRequest financeDetailRequest, @PathVariable("id") int id){
        return DfResponse
                .okEntity(financeDetailService.update(id, financeDetailRequest));
    }

    @DeleteMapping("/finance-detail/{id}")
    public ResponseEntity<DfResponse<FinanceDetailResponse>> delete(@PathVariable("id") int id){
        return DfResponse
                .okEntity(financeDetailService.deleteById(id));
    }

    @PostMapping("/finance-detail/search")
    public ResponseEntity<DfResponse<Page<FinanceDetailResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(financeDetailService.search(searchRequest));
    }

}
