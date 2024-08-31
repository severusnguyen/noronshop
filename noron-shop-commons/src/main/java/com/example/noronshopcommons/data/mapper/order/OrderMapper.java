package com.example.noronshopcommons.data.mapper.order;

import com.example.noronshopcommons.config.JsonMapper;
import com.example.noronshopcommons.data.dto.request.OrderRequest;
import com.example.noronshopcommons.data.dto.request.ShippingRequest;
import com.example.noronshopcommons.data.dto.response.OrderResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.mapper.order_detail.OrderDetailMapper;
import com.example.noronshopcommons.data.modal.OrderShipping;
import com.example.noronshopcommons.data.tables.pojos.Order;
import com.example.noronshopcommons.data.tables.pojos.OrderDetail;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.utils.CollectionUtils;
import lombok.SneakyThrows;
import org.jooq.JSON;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class OrderMapper extends BaseMap<OrderRequest, OrderResponse, Order> {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    @Named("toPOJO")
    @Mapping(target = "shipping", ignore = true)
    public abstract Order toPOJO(OrderRequest request) ;

    @Override
    @Named("toResponse")
    @Mapping(target = "shippingAddress", ignore = true)
    public abstract OrderResponse toResponse(Order pojo);

    @Named("toResponse")
    @Mapping(target = "shippingAddress", ignore = true)
    public abstract OrderResponse toResponseCustom(Order pojo, List<OrderDetail> orderDetails,@Context Map<Integer, Product> productMap);

    public List<OrderResponse> toResponsesCustom(List<Order> orderList,
                                           Map<Integer, List<OrderDetail>>  orderDetailMap,
                                           Map<Integer, Product> productMap
    ){

        return orderList.stream()
                .map(order -> toResponseCustom(order, orderDetailMap.get(order.getId()), productMap))
                .collect(Collectors.toList());

    }

    @SneakyThrows
    @AfterMapping
    public void afterToPojo(@MappingTarget Order order, OrderRequest orderRequest){
        if (orderRequest.getShippingAddress() != null){
            String shippingAddress = JsonMapper.getObjectMapper().writeValueAsString(order.getShipping());
            order.setShipping(JSON.valueOf(shippingAddress));
        }
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget OrderResponse orderResponse, Order order,List<OrderDetail> orderDetails ,@Context Map<Integer, Product> productMap){
        orderResponse.setShippingAddress(CollectionUtils.getOrDefaultList(OrderShipping.class, order.getShipping(), new ArrayList<>()));
        orderResponse.setOrderDetails(orderDetailMapper.toResponses(orderDetails, productMap));
    }
}
