package com.example.noronshopcommons.data.mapper.finance_details_mapper;

import com.example.noronshopcommons.data.dto.request.FinanceDetailRequest;
import com.example.noronshopcommons.data.dto.response.FinanceDetailResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.mapper.order.OrderMapper;
import com.example.noronshopcommons.data.mapper.user.UserMapper;
import com.example.noronshopcommons.data.tables.pojos.FinanceDetail;
import com.example.noronshopcommons.data.tables.pojos.Order;
import com.example.noronshopcommons.data.tables.pojos.User;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class FinanceDetailMapper extends BaseMap<FinanceDetailRequest, FinanceDetailResponse, FinanceDetail> {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    public List<FinanceDetailResponse> toCustomResponse(List<FinanceDetail> financeDetails, Map<Integer, Order> orderMap, Map<Integer, User> userMap) {
        return financeDetails.stream().map(financeDetail -> {
            FinanceDetailResponse financeDetailResponse = toResponse(financeDetail);
            if (financeDetail.getOrderId() != null && !orderMap.isEmpty()) {
                financeDetailResponse.setOrder(orderMapper.toResponse(orderMap.get(financeDetail.getOrderId())));
            }
            if (financeDetail.getUserId() != null && !userMap.isEmpty()) {
                financeDetailResponse.setUser(userMapper.toResponse(userMap.get(financeDetail.getUserId())));
            }
            return financeDetailResponse;
        }).collect(Collectors.toList());
    }

}
