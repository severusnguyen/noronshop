package com.example.noronshopcommons.data.mapper.order_detail;

import com.example.noronshopcommons.data.dto.request.OrderDetailRequest;
import com.example.noronshopcommons.data.dto.response.OrderDetailResponse;
import com.example.noronshopcommons.data.dto.response.OrderResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.mapper.ProductMapper;
import com.example.noronshopcommons.data.modal.OrderShipping;
import com.example.noronshopcommons.data.tables.pojos.Category;
import com.example.noronshopcommons.data.tables.pojos.Order;
import com.example.noronshopcommons.data.tables.pojos.OrderDetail;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.utils.CollectionUtils;
import lombok.SneakyThrows;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class OrderDetailMapper extends BaseMap<OrderDetailRequest, OrderDetailResponse, OrderDetail> {

    @Autowired
    private ProductMapper productMapper;

    public abstract OrderDetailResponse toResponse(OrderDetail pojo, @Context Product product);

    public List<OrderDetailResponse> toResponses(List<OrderDetail> orderDetailList, Map<Integer, Product> productMap) {
        return orderDetailList.stream()
                .map(orderDetail -> toResponse(orderDetail, productMap.get(orderDetail.getProductId())))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget OrderDetailResponse orderDetailResponse, @Context Product product){
        orderDetailResponse.setProduct(productMapper.toResponse(product));
    }

}
