package com.example.noronshoprepository.repository.form_return;

import com.example.noronshopcommons.data.tables.pojos.FormReturn;
import com.example.noronshopcommons.data.tables.records.FormReturnRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.noronshopcommons.data.Tables.FORM_RETURN;

@Repository
public class FormReturnRepository extends AbsRepository<FormReturnRecord, FormReturn, Integer> {

    @Override
    protected TableImpl<FormReturnRecord> getTable() {
        return FORM_RETURN;
    }
}
