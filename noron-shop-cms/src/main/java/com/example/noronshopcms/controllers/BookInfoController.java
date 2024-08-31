package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.book_info.BookInfoServiceImp;
import com.example.noronshopcommons.data.dto.request.BookInfoRequest;
import com.example.noronshopcommons.data.dto.response.BookInfoResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/book")
public class BookInfoController {

    @Autowired
    BookInfoServiceImp bookInfoServiceImp;

    @PostMapping("")
    public ResponseEntity<DfResponse<BookInfoResponse>> insert(@RequestBody BookInfoRequest bookRequest){
        return DfResponse
                .okEntity(bookInfoServiceImp.insert(bookRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<BookInfoResponse>> update(@PathVariable int id, @RequestBody BookInfoRequest bookRequest){
        return DfResponse
                .okEntity(bookInfoServiceImp.update(id, bookRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<BookInfoResponse>> delete(@PathVariable int id){
        return DfResponse
                .okEntity(bookInfoServiceImp.deleteById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<BookInfoResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(bookInfoServiceImp.searchCustom(searchRequest));
    }
}
