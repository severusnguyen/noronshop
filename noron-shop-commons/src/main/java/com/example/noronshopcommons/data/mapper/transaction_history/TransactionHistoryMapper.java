package com.example.noronshopcommons.data.mapper.transaction_history;

import com.example.noronshopcommons.data.dto.request.TransactionHistoryRequest;
import com.example.noronshopcommons.data.dto.response.ShopResponse;
import com.example.noronshopcommons.data.dto.response.TransactionHistoryResponse;
import com.example.noronshopcommons.data.dto.response.UserResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.mapper.shop.ShopMapper;
import com.example.noronshopcommons.data.mapper.user.UserMapper;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.data.tables.pojos.TransactionHistory;
import com.example.noronshopcommons.data.tables.pojos.User;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class TransactionHistoryMapper extends BaseMap<TransactionHistoryRequest, TransactionHistoryResponse, TransactionHistory> {

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    UserMapper userMapper;

    public List<TransactionHistoryResponse> toResponseCustom(List<TransactionHistory> transactionHistories, Map<Integer, Shop> shopMap){
        return transactionHistories.stream().map(transactionHistory -> {
            TransactionHistoryResponse transactionHistoryResponse = toResponse(transactionHistory);
            Integer shopId = transactionHistory.getShopId();
            if (shopId != null){
                ShopResponse shopResponse = shopMapper.toResponse(shopMap.get(shopId));
                transactionHistoryResponse.setShop(shopResponse);
            }
            return transactionHistoryResponse;
        }).collect(Collectors.toList());
    }

    public List<TransactionHistoryResponse> toResponsesCustom(List<TransactionHistory> transactionHistories, Map<Integer, Shop> shopMap, Map<Integer, User> userMap){
        return transactionHistories.stream().map(transactionHistory -> {
            TransactionHistoryResponse transactionHistoryResponse = toResponse(transactionHistory);
            Integer shopId = transactionHistory.getShopId();
            Integer userId = transactionHistory.getUserId();
            if (shopId != null){
                ShopResponse shopResponse = shopMapper.toResponse(shopMap.get(shopId));
                transactionHistoryResponse.setShop(shopResponse);
            }
            if (userId != null){
                UserResponse userResponse = userMapper.toResponse(userMap.get(userId));
                transactionHistoryResponse.setUser(userResponse);
            }
            return transactionHistoryResponse;
        }).collect(Collectors.toList());
    }
}
