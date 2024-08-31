package com.example.noronshopcommons.data.mapper.cart;

import com.example.noronshopcommons.config.JsonMapper;
import com.example.noronshopcommons.data.dto.request.CartRequest;
import com.example.noronshopcommons.data.dto.response.CartResponse;
import com.example.noronshopcommons.data.dto.response.ProductResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.modal.CartProduct;
import com.example.noronshopcommons.data.tables.pojos.Cart;
import com.example.noronshopcommons.utils.CollectionUtils;
import lombok.SneakyThrows;
import org.jooq.JSON;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public abstract class CartMapper extends BaseMap<CartRequest, CartResponse, Cart> {

    @Override
    @Named("toPOJO")
    @Mappings({
            @Mapping(target = "products", ignore = true),
    })
    public abstract Cart toPOJO(CartRequest request);

    @Override
    @Named("toResponse")
    @Mappings({
            @Mapping(target = "products", ignore = true),
    })
    public abstract CartResponse toResponse(Cart pojo);

    @SneakyThrows
    @AfterMapping
    public void afterToPojo(@MappingTarget Cart cart, CartRequest request){
        if (request.getProducts() != null){
            String product = JsonMapper.getObjectMapper().writeValueAsString(request.getProducts());
            cart.setProducts(JSON.valueOf(product));
        }
    }

    @AfterMapping
    public void afterToResponse(@MappingTarget CartResponse cartResponse, Cart cart){
        cartResponse.setProducts(CollectionUtils.getOrDefaultList(CartProduct.class, cart.getProducts(), new ArrayList<>()));
    }
}
