package com.example.noronshopcms.service.tiki_products_price;

import com.example.noronshopcommons.data.dto.response.TikiProductPriceResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;

public interface ITikiProductPriceService {
    Page<TikiProductPriceResponse> searchCustom(SearchRequest searchRequest);
}
