package com.example.noronshopcommons.data.mapper.finance;

import com.example.noronshopcommons.data.dto.request.FinanceRequest;
import com.example.noronshopcommons.data.dto.response.FinanceResponse;
import com.example.noronshopcommons.data.dto.response.ShopResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.mapper.shop.ShopMapper;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.data.tables.pojos.Finance;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class FinanceMapper extends BaseMap<FinanceRequest, FinanceResponse, Finance> {

    @Autowired
    ShopMapper shopMapper;

    public List<FinanceResponse> toCustomResponse(List<Finance> finances, Map<Integer, Shop> shopMap){
        return finances.stream().map(finance -> {
            FinanceResponse financeResponse = toResponse(finance);
            if (finance.getShopId() != null && !shopMap.isEmpty()){
                Shop shop = shopMap.get(finance.getShopId());
                ShopResponse shopResponse = shopMapper.toResponse(shop);
                financeResponse.setShop(shopResponse);
            }
            return financeResponse;
        }).collect(Collectors.toList());
    }

}
