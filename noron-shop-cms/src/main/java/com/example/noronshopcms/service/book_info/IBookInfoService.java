package com.example.noronshopcms.service.book_info;

import com.example.noronshopcommons.data.dto.response.BookInfoResponse;
import com.example.noronshopcommons.data.dto.response.ProductResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;

public interface IBookInfoService {
    Page<BookInfoResponse> searchCustom(SearchRequest searchRequest);
}
