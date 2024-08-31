package com.example.noronshopcms.service.vendor;

import com.example.noronshopcommons.data.dto.response.VendorResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.Shop;

import java.util.Map;

public interface IVendorService {
    Page<VendorResponse> searchCustom(SearchRequest searchRequest);
}
