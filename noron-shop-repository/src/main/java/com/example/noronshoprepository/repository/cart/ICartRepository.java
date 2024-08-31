package com.example.noronshoprepository.repository.cart;

import com.example.noronshopcommons.data.tables.pojos.Cart;

import java.util.Optional;

public interface ICartRepository {
    Optional<Cart> getCartByUserId(Long id);
}
