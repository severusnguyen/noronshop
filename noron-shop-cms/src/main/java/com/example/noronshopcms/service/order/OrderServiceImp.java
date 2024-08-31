package com.example.noronshopcms.service.order;

import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.request.OrderRequest;
import com.example.noronshopcommons.data.dto.response.OrderResponse;
import com.example.noronshopcommons.data.mapper.order.OrderMapper;
import com.example.noronshopcommons.data.tables.pojos.Order;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.order.OrderRepositoryImp;
import org.springframework.stereotype.Service;
import com.example.noronshopcms.service.AbsCmsService;

import static com.example.noronshopcms.service.constant.OrderConstant.NOT_FOUND;

@Service
public class OrderServiceImp extends AbsCmsService<OrderRequest, OrderResponse, Order, Integer, OrderRepositoryImp, OrderMapper>{

    public OrderServiceImp(OrderRepositoryImp repositoryImp, OrderMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }

    @Log
    @Override
    public OrderResponse update(Integer integer, OrderRequest request) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.update(integer, request);
    }

    @Log
    @Override
    public OrderResponse deleteById(Integer integer) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.deleteById(integer);
    }
}
