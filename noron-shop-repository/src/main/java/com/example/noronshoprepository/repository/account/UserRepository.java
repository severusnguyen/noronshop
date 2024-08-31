package com.example.noronshoprepository.repository.account;

import com.example.noronshopcommons.data.tables.pojos.Role;
import com.example.noronshopcommons.data.tables.pojos.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findUserByUsername(String username);
    List<Role> findRolesByUserId(int userId);
    Optional<User> findUserById(Integer userId);
    List<User> findUserByUserIds(List<Long> userId);
}
