package com.example.noronshopapi.service.order;

import com.example.noronshopcommons.data.dto.response.OrderResponse;
import com.example.noronshopcommons.data.modal.paging.Page;

public interface IOrderService {
    Page<OrderResponse> findOrderByUserId(Integer id);
}
