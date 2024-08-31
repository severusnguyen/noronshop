package com.example.noronshoprepository.repository.order_detail;

import com.example.noronshopcommons.data.tables.pojos.OrderDetail;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IOrderDetailRepository {
    List<OrderDetail> getOrderDetailByOrderId(Collection<Integer> orderId);
}
