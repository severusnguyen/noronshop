package com.example.noronshopcms.service.product;

import com.example.noronshopcommons.data.dto.response.ProductResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;


public interface IProductCmsService {
    Page<ProductResponse> searchCustomRes(SearchRequest searchRequest);
}
