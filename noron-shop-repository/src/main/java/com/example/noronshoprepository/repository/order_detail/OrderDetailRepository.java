package com.example.noronshoprepository.repository.order_detail;

import com.example.noronshopcommons.data.tables.pojos.OrderDetail;
import com.example.noronshopcommons.data.tables.records.OrderDetailRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.example.noronshopcommons.data.Tables.ORDER_DETAIL;

@Repository
public class OrderDetailRepository extends AbsRepository<OrderDetailRecord, OrderDetail, Integer> implements IOrderDetailRepository{

    @Override
    protected TableImpl<OrderDetailRecord> getTable() {
        return ORDER_DETAIL;
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(Collection<Integer> orderId) {
        return dslContext.selectFrom(ORDER_DETAIL)
                .where(ORDER_DETAIL.ORDER_ID.in(orderId))
                .fetchInto(OrderDetail.class);
    }


}
