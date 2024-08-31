package com.example.noronshoprepository.repository.cart;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.tables.pojos.Cart;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.data.tables.records.CartRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.noronshopcommons.data.tables.Cart.CART;
import static com.example.noronshopcommons.data.tables.Product.PRODUCT;

@Repository
public class CartRepositoryImpl extends AbsRepository<CartRecord, Cart, Integer> implements ICartRepository{

    @Override
    protected TableImpl<CartRecord> getTable() {
        return CART;
    }

    @Override
    public Optional<Cart> getCartByUserId(Long id) {
        return Optional.ofNullable( dslContext.selectFrom(CART)
                .where(CART.USER_ID.eq(id))
                .fetchOneInto(Cart.class));
    }
}
