package com.example.noronshoprepository.repository.user_account_bank;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.tables.pojos.UserAccountBank;
import com.example.noronshopcommons.data.tables.records.UserAccountBankRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountBankRepositoryImp extends AbsRepository<UserAccountBankRecord, UserAccountBank, Integer> {

    @Override
    protected TableImpl<UserAccountBankRecord> getTable() {
        return Tables.USER_ACCOUNT_BANK;
    }
}
