package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.user_account_bank.UserAccountBankServiceImp;
import com.example.noronshopcommons.data.dto.request.UserAccountBankRequest;
import com.example.noronshopcommons.data.dto.response.UserAccountBankResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/user-acount-bank")
public class UserAccountBankController {

    @Autowired
    UserAccountBankServiceImp serviceImp;

    @PostMapping("")
    public ResponseEntity<DfResponse<UserAccountBankResponse>> insert(@RequestBody UserAccountBankRequest request){
        return DfResponse
                .okEntity(serviceImp.insert(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<UserAccountBankResponse>> update(@PathVariable int id, @RequestBody UserAccountBankRequest request){
        return DfResponse
                .okEntity(serviceImp.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<UserAccountBankResponse>> delete(@PathVariable int id){
        return DfResponse
                .okEntity(serviceImp.deleteById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<UserAccountBankResponse>>> search(@RequestBody SearchRequest request){
        return DfResponse
                .okEntity(serviceImp.search(request));
    }
}
