package com.example.noronshopapi.service.order_detail;

import com.example.noronshopapi.service.AbsService;
import com.example.noronshopcommons.data.dto.request.OrderDetailRequest;
import com.example.noronshopcommons.data.dto.response.OrderDetailResponse;
import com.example.noronshopcommons.data.mapper.order_detail.OrderDetailMapper;
import com.example.noronshopcommons.data.tables.pojos.Order;
import com.example.noronshopcommons.data.tables.pojos.OrderDetail;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.order.OrderRepositoryImp;
import com.example.noronshoprepository.repository.order_detail.OrderDetailRepository;
import com.example.noronshoprepository.repository.product.ProductRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.noronshopcommons.data.constrant.BaseConstant.INSERT_FAIL;
import static com.example.noronshopcommons.data.constrant.OrderDetailConstant.*;


@Slf4j
@Service
public class OrderDetailService extends AbsService<OrderDetailRequest, OrderDetailResponse, OrderDetail, Integer,
        OrderDetailRepository, OrderDetailMapper> implements IOrderDetailService{

    public OrderDetailService(OrderDetailRepository repository, OrderDetailMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Autowired
    private ProductRepositoryImpl productRepository;

    @Autowired
    private OrderRepositoryImp orderRepositoryImp;

    @Override
    public String insertMultiOrderDetailRecord(List<OrderDetailRequest> requestList) {
        try {
            Set<Integer> productIds = requestList.stream()
                    .map(OrderDetailRequest::getProductId)
                    .collect(Collectors.toSet());
            if (productIds.isEmpty()) {
                throw new ApiException(PRODUCT_ID_EMPTY);
            }
            List<Product> productList = productRepository.getListProductByIds(productIds);
            productList.stream()
                    .filter(product -> !productIds.contains(product.getId()))
                    .findAny()
                    .ifPresent(product -> {
                        throw new ApiException(PRODUCT_NOT_FOUND);
                    });

            Set<Integer> orderIds = requestList.stream()
                    .map(OrderDetailRequest::getOrderId)
                    .collect(Collectors.toSet());
            if (orderIds.isEmpty()) {
                throw new ApiException(ORDER_ID_EMPTY);
            }
            List<Order> orderList = orderRepositoryImp.getListOrderById(orderIds);
            orderList.stream()
                    .filter(order -> !orderIds.contains(order.getId()))
                    .findAny()
                    .ifPresent(order -> {
                        throw new ApiException(ORDER_NOT_FOUND);
                    });

            List<OrderDetail> orderDetails = mapper.toPOJOs(requestList);
            List<Integer> numberExcused = repository.insert(orderDetails);
            return "The number record has excused: " + numberExcused.size();
        }catch (Exception e){
            log.error("Error insertMultiOrderDetailRecord {}: ", e.getMessage());
            throw new ApiException(INSERT_FAIL);
        }
    }
}
