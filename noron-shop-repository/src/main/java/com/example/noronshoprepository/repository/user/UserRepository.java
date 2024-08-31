package com.example.noronshoprepository.repository.user;

import static com.example.noronshopcommons.data.Tables.USER;
import com.example.noronshopcommons.data.tables.pojos.User;
import com.example.noronshopcommons.data.tables.records.UserRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends AbsRepository<UserRecord, User, Integer> implements IUserRepository {
    @Override
    protected TableImpl<UserRecord> getTable() {
        return USER;
    }

    @Override
    public List<User> getUserByIds(List<Integer> userIds) {
        return dslContext.selectFrom(USER)
                .where(USER.ID.in(userIds))
                .fetchInto(User.class);
    }
}
