package com.example.noronshopapi.service.order;

import com.example.noronshopapi.service.AbsService;
import com.example.noronshopcommons.data.dto.request.OrderRequest;
import com.example.noronshopcommons.data.dto.response.OrderResponse;
import com.example.noronshopcommons.data.mapper.order.OrderMapper;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.OrderDetail;
import com.example.noronshopcommons.data.tables.pojos.Order;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.account.UserRepositoryImp;
import com.example.noronshoprepository.repository.order.OrderRepositoryImp;
import com.example.noronshoprepository.repository.order_detail.OrderDetailRepository;
import com.example.noronshoprepository.repository.product.ProductRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.noronshopcommons.data.constrant.OrderConstant.*;

@Slf4j
@Service
public class OrderService extends AbsService<OrderRequest, OrderResponse, Order, Integer, OrderRepositoryImp, OrderMapper>
    implements IOrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepositoryImp userRepository;

    @Autowired
    private ProductRepositoryImpl productRepository;

    public OrderService(OrderRepositoryImp orderRepositoryImp, OrderMapper orderMapper) {
        this.repository = orderRepositoryImp;
        this.mapper = orderMapper;
    }

    @Override
    public Page<OrderResponse> findOrderByUserId(Integer id) {
        try {
            userRepository.findById(id)
                    .orElseThrow(() -> new ApiException(USER_ID_NOT_FOUND, 400));

            List<Order> orderList = repository.getOrderByUserId(id);
            Set<Integer> orderIds = orderList.stream()
                    .map(Order::getId)
                    .collect(Collectors.toSet());
            Long count = (long) orderIds.size();

            List<OrderDetail> orderDetailList =
                    orderDetailRepository.getOrderDetailByOrderId(orderIds);

            Map<Integer, List<OrderDetail>> orderDetailMap = orderDetailList.stream()
                    .filter(od -> orderIds.contains(od.getOrderId()) && od.getProductId() != null)
                    .collect(Collectors.groupingBy(OrderDetail::getOrderId));

            Set<Integer> productIds = orderDetailList.stream()
                    .map(OrderDetail::getProductId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            List<Product> productList = productRepository.getListProductByIds(productIds);

            Map<Integer, Product> productMap = productList.stream()
                    .collect(Collectors.toMap(Product::getId, p -> p));

            List<OrderResponse> orderResponseList = mapper.toResponsesCustom(orderList, orderDetailMap, productMap);
            return new Page<OrderResponse>()
                    .setItems(orderResponseList)
                    .setTotal(count);
        }catch (Exception e){
            log.error("Error findOrderByUserId {}:", e.getMessage());
            throw new ApiException(FIND_ORDER_NOT_SUCCESS, 400);
        }
    }
}
