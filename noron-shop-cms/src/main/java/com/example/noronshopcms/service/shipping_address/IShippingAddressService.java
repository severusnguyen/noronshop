package com.example.noronshopcms.service.shipping_address;

import com.example.noronshopcommons.data.dto.response.ShippingAddressResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.stereotype.Service;

@Service
public interface IShippingAddressService {
    Page<ShippingAddressResponse> searchCustom(SearchRequest searchRequest);
}
