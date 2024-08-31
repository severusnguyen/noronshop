package com.example.noronshopcommons.data.mapper.shop;

import com.example.noronshopcommons.config.JsonMapper;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.dto.request.BusinessItemsRequest;
import com.example.noronshopcommons.data.dto.request.IdentityCardRequest;
import com.example.noronshopcommons.data.dto.request.ShopRequest;
import com.example.noronshopcommons.data.dto.response.ShopResponse;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.utils.CollectionUtils;
import lombok.SneakyThrows;

import org.jooq.JSON;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public abstract class ShopMapper extends BaseMap<ShopRequest, ShopResponse, Shop> {

    @SneakyThrows
    @AfterMapping
    public void afterToPojo(@MappingTarget Shop shop, ShopRequest shopRequest){

        if (shopRequest.getIdentityCards() != null){
            String identityCard = JsonMapper.getObjectMapper().writeValueAsString(shopRequest.getIdentityCards());
            shop.setIdentityCard(JSON.valueOf(identityCard));
        }
        if (shopRequest.getBusinessItem() != null){
            String businessItem = JsonMapper.getObjectMapper().writeValueAsString(shopRequest.getBusinessItem());
            shop.setBusinessItems(JSON.valueOf(businessItem));
        }
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget ShopResponse shopResponse, Shop shop){
        shopResponse.setIdentityCards(CollectionUtils.getOrDefaultList(IdentityCardRequest.class, shop.getIdentityCard(), new ArrayList<>()));
        shopResponse.setBusinessItem(CollectionUtils.getOrDefaultList(BusinessItemsRequest.class, shop.getBusinessItems(), new ArrayList<>()));
    }
}
