package com.example.noronshoprepository.repository.addressshop;


import com.example.noronshopcommons.data.tables.pojos.AddressShop;
import com.example.noronshopcommons.data.tables.records.AddressShopRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.noronshopcommons.data.Tables.ADDRESS_SHOP;

@Repository
public class AddressShopRepository extends AbsRepository<AddressShopRecord, AddressShop, Integer> implements IAddressShopRepository {
    @Override
    protected TableImpl<AddressShopRecord> getTable() {
        return ADDRESS_SHOP;
    }
}
