package com.example.noronshoprepository.repository.shipping_repository;

import com.example.noronshopcommons.data.tables.pojos.ShippingAddress;
import com.example.noronshopcommons.data.tables.records.ShippingAddressRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.noronshopcommons.data.tables.ShippingAddress.SHIPPING_ADDRESS;

@Repository
public class ShippingAddressRepository extends AbsRepository<ShippingAddressRecord, ShippingAddress, Integer> {

    @Override
    protected TableImpl<ShippingAddressRecord> getTable() {
        return SHIPPING_ADDRESS;
    }
}
