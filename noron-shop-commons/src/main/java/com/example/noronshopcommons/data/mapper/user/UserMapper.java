package com.example.noronshopcommons.data.mapper.user;

import com.example.noronshopcommons.data.dto.request.UserRequest;
import com.example.noronshopcommons.data.dto.response.UserResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.tables.pojos.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper extends BaseMap<UserRequest, UserResponse, User> {
}
