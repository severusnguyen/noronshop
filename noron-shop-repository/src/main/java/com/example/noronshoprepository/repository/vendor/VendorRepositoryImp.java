package com.example.noronshoprepository.repository.vendor;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.tables.pojos.Vendor;
import com.example.noronshopcommons.data.tables.records.VendorRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class VendorRepositoryImp extends AbsRepository<VendorRecord, Vendor, Integer> implements IVenderRepository{
    @Override
    protected TableImpl<VendorRecord> getTable() {
        return Tables.VENDOR;
    }
}
