package com.example.noronshopapi.service.cart;

import com.example.noronshopapi.service.AbsService;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.request.CartRequest;
import com.example.noronshopcommons.data.dto.response.CartResponse;
import com.example.noronshopcommons.data.mapper.cart.CartMapper;
import com.example.noronshopcommons.data.tables.pojos.Cart;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.account.UserRepositoryImp;
import com.example.noronshoprepository.repository.cart.CartRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.noronshopcommons.data.constrant.CartConstant.*;

import java.util.UUID;

@Slf4j
@Service
public class CartServiceImpl extends AbsService<CartRequest, CartResponse,
        Cart, Integer, CartRepositoryImpl, CartMapper> implements ICartService{

    @Autowired
    private UserRepositoryImp userRepositoryImp;

    public CartServiceImpl(CartRepositoryImpl repository, CartMapper mapper){
        this.mapper = mapper;
        this.repository = repository;
    }
    
    @Override
    public CartResponse initCart(CartRequest request) {
        try {
            UUID uuid = UUID.randomUUID();
            request.setCartToken(uuid.toString());
            Integer idUser = Math.toIntExact(request.getUserId());
            userRepositoryImp.findUserById(idUser)
                    .orElseThrow(() -> new ApiException(USER_NOT_FOUND));

            Cart cart = mapper.toPOJO(request);
            Cart insetCart = repository.insertReturning(cart).get();
            if (insetCart == null) {
                throw new ApiException(INIT_CART_FAIL);
            }

            return mapper.toResponse(insetCart);
        }catch (Exception e){
            log.error("Error initCart {}: ",e.getMessage());
            throw new ApiException(INIT_CART_FAIL);
        }
    }

    @Log
    @Override
    public CartResponse getCart(CartRequest request) {
        try {
            Integer idUser = Math.toIntExact(request.getUserId());
            userRepositoryImp.findUserById(idUser)
                    .orElseThrow(() -> new ApiException(USER_NOT_FOUND));

            Cart cart = repository.getCartByUserId(request.getUserId())
                    .orElseThrow(() -> new ApiException(GET_CART_FAIL));

            return mapper.toResponse(cart);
        }catch (Exception e){
            log.error("Error getCart {}: ",e.getMessage());
            throw new ApiException(GET_CART_FAIL);
        }
    }
}
