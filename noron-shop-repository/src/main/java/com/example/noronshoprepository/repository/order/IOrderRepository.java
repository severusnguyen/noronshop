package com.example.noronshoprepository.repository.order;

import com.example.noronshopcommons.data.tables.pojos.Order;

import java.util.Collection;
import java.util.List;

public interface IOrderRepository {
    List<Order> getOrderByIds(List<Integer> orderIds);
    List<Order> getOrderByUserId(Integer userId);
    List<Order> getListOrderById(Collection<Integer> userIds);
}
