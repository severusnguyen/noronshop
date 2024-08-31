package com.example.noronshoprepository.repository.transaction_history;


import com.example.noronshopcommons.data.tables.pojos.TransactionHistory;
import com.example.noronshopcommons.data.tables.records.TransactionHistoryRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.noronshopcommons.data.Tables.TRANSACTION_HISTORY;

@Repository
public class TransactionHistoryRepository extends AbsRepository<TransactionHistoryRecord, TransactionHistory, Integer> implements ITransactionHistoryRepository {
    @Override
    protected TableImpl<TransactionHistoryRecord> getTable() {
        return TRANSACTION_HISTORY;
    }
}
