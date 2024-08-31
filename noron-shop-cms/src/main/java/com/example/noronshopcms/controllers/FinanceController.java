package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.finance.FinanceService;
import com.example.noronshopcommons.data.dto.request.FinanceRequest;
import com.example.noronshopcommons.data.dto.response.FinanceResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms")
public class FinanceController {

    FinanceService financeService;

    @Autowired
    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @PostMapping("/finance")
    public ResponseEntity<DfResponse<FinanceResponse>> insert(@RequestBody FinanceRequest financeRequest){
        return DfResponse
                .okEntity(financeService.insert(financeRequest));
    }

    @PutMapping("/finance")
    public ResponseEntity<DfResponse<FinanceResponse>> update(@PathVariable("id") int id, FinanceRequest financeRequest){
        return DfResponse
                .okEntity(financeService.update(id, financeRequest));
    }

    @DeleteMapping("/finance")
    public ResponseEntity<DfResponse<FinanceResponse>> delete(@PathVariable("id") int id){
        return DfResponse
                .okEntity(financeService.deleteById(id));
    }

    @PostMapping("/finance/search")
    public ResponseEntity<DfResponse<Page<FinanceResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(financeService.search(searchRequest));
    }


}
