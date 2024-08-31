package com.example.noronshoprepository.repository.order;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.tables.pojos.Order;
import com.example.noronshopcommons.data.tables.records.OrderRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.noronshopcommons.data.Tables.ORDER;

import java.util.Collection;
import java.util.List;

@Repository
public class OrderRepositoryImp extends AbsRepository<OrderRecord, Order, Integer> implements IOrderRepository {
    @Override
    protected TableImpl<OrderRecord> getTable() {
        return Tables.ORDER;
    }

    @Override
    public List<Order> getOrderByIds(List<Integer> orderIds) {
        return dslContext.selectFrom(Tables.ORDER)
                .where(Tables.ORDER.ID.in(orderIds))
                .fetchInto(Order.class);
    }

    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        return dslContext.selectFrom(ORDER)
                .where(ORDER.USER_ID.eq(Long.valueOf(userId)))
                .fetchInto(Order.class);
    }

    @Override
    public List<Order> getListOrderById(Collection<Integer> ids) {
        return dslContext.selectFrom(ORDER)
                .where(ORDER.ID.in(ids))
                .fetchInto(Order.class);
    }
}
