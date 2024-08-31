package com.example.noronshoprepository.repository.user;

import com.example.noronshopcommons.data.tables.pojos.User;

import java.util.List;

public interface IUserRepository {

    public List<User> getUserByIds(List<Integer> userIds);

}
