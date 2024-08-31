package com.example.noronshopapi.controller;

import com.example.noronshopapi.service.transaction_history.ITransactionHistoryService;
import com.example.noronshopcommons.data.dto.response.TransactionHistoryResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class TransactionHistoryController {

    @Autowired
    private ITransactionHistoryService transactionHistoryService;

    @GetMapping("/histories/search")
    public ResponseEntity<DfResponse<Page<TransactionHistoryResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(transactionHistoryService.search(searchRequest));
    }
}
