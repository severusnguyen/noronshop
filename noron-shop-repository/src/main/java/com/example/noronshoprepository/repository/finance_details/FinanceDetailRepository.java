package com.example.noronshoprepository.repository.finance_details;


import com.example.noronshopcommons.data.tables.pojos.FinanceDetail;
import com.example.noronshopcommons.data.tables.records.FinanceDetailRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.noronshopcommons.data.Tables.FINANCE_DETAIL;

@Repository
public class FinanceDetailRepository extends AbsRepository<FinanceDetailRecord, FinanceDetail, Integer> implements IFinanceDetailRepository {
    @Override
    protected TableImpl<FinanceDetailRecord> getTable() {
        return FINANCE_DETAIL;
    }
}
