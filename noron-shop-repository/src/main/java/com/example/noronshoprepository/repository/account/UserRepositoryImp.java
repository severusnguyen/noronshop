package com.example.noronshoprepository.repository.account;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.tables.pojos.Role;
import com.example.noronshopcommons.data.tables.pojos.User;
import com.example.noronshopcommons.data.tables.records.UserRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImp extends AbsRepository<UserRecord, User, Integer> implements UserRepository {

    @Override
    public Optional<User> findUserByUsername(String username) {
        return dslContext.selectFrom(Tables.USER)
                .where(Tables.USER.EMAIL.eq(username).or(Tables.USER.PHONE.eq(username)))
                .fetchOptionalInto(User.class);
    }

    @Override
    public List<Role> findRolesByUserId(int userId) {
        return dslContext.select(Tables.ROLE.NAME)
                .from(Tables.ROLE)
                .join(Tables.USER_ROLE).on(Tables.ROLE.ID.eq(Tables.USER_ROLE.ID_ROLE))
                .fetchInto(Role.class);
    }

    @Override
    public Optional<User>findUserById(Integer userId) {
        return Optional.ofNullable(dslContext.selectFrom(Tables.USER)
                .where(Tables.USER.ID.eq(userId))
                .fetchOneInto(User.class));
    }

    @Override
    public List<User> findUserByUserIds(List<Long> userId) {
        return dslContext.select(Tables.USER.ID, Tables.USER.AVATAR, Tables.USER.NAME, Tables.USER.STATUS)
                .from(Tables.USER)
                .where(Tables.USER.ID.in(userId))
                .fetchInto(User.class);
    }

    @Override
    protected TableImpl<UserRecord> getTable() {
        return Tables.USER;
    }
}
