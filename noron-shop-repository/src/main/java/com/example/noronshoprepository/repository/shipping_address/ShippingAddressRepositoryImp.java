package com.example.noronshoprepository.repository.shipping_address;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.tables.pojos.ShippingAddress;
import com.example.noronshopcommons.data.tables.records.ShippingAddressRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ShippingAddressRepositoryImp extends AbsRepository<ShippingAddressRecord, ShippingAddress, Integer> {

    @Override
    protected TableImpl<ShippingAddressRecord> getTable() {
        return Tables.SHIPPING_ADDRESS;
    }
}
