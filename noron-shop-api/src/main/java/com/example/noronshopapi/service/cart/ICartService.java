package com.example.noronshopapi.service.cart;

import com.example.noronshopapi.service.IService;
import com.example.noronshopcommons.data.dto.request.CartRequest;
import com.example.noronshopcommons.data.dto.response.CartResponse;

public interface ICartService {
    CartResponse initCart(CartRequest request);
    CartResponse getCart(CartRequest request);
}
