package com.example.noronshopcms.service.tiki_products;

import com.example.noronshopcommons.data.dto.response.TikiProductsResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;

public interface ITikiProductsService {
    Page<TikiProductsResponse> searchCustom(SearchRequest searchRequest);
}
