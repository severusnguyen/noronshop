package com.example.noronshopcommons.data.mapper.categoryshop;

import com.example.noronshopcommons.data.dto.response.CategoryShopResponse;
import com.example.noronshopcommons.data.dto.response.ShopResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.dto.request.CategoryShopRequest;
import com.example.noronshopcommons.data.mapper.shop.ShopMapper;
import com.example.noronshopcommons.data.tables.pojos.CategoryShop;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Slf4j
public abstract class CategoryShopMapper extends BaseMap<CategoryShopRequest, CategoryShopResponse, CategoryShop> {

    @Autowired
    ShopMapper shopMapper;

    public List<CategoryShopResponse> toCustomResponse(List<CategoryShop> categoryShops, Map<Integer, Shop> shops){
        return categoryShops.stream().map(categoryShop -> {
            CategoryShopResponse categoryShopResponse = toResponse(categoryShop);
            ShopResponse shopResponse = new ShopResponse();
            try {
                if (categoryShop.getShopId() != null && !shops.isEmpty()){
                    Shop shop = shops.get(categoryShop.getShopId());
                    shopResponse = shopMapper.toResponse(shop);
                    categoryShopResponse.setShopResponse(shopResponse);
                }
            }catch (Exception exception){
                log.error("[ERROR] {toCustomResponse} " + exception.getMessage());
            }
            return categoryShopResponse;
        }).collect(Collectors.toList());
    }

}
