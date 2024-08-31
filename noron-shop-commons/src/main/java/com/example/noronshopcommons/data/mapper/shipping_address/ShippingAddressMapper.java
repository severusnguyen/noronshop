package com.example.noronshopcommons.data.mapper.shipping_address;

import com.example.noronshopcommons.data.dto.request.ShippingAddressRequest;
import com.example.noronshopcommons.data.dto.response.ShippingAddressResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.mapper.user.UserMapper;
import com.example.noronshopcommons.data.tables.pojos.ShippingAddress;
import com.example.noronshopcommons.data.tables.pojos.User;
import lombok.SneakyThrows;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ShippingAddressMapper extends BaseMap<ShippingAddressRequest, ShippingAddressResponse, ShippingAddress> {

    @Autowired
    UserMapper userMapper;

    public abstract ShippingAddressResponse toResponseCustom(ShippingAddress shippingAddress,@Context User user);

    public List<ShippingAddressResponse> toResponsesCustom(List<ShippingAddress> shippingAddresses, Map<Integer, User> userMap){
        return shippingAddresses.stream()
                .map(shippingAddress -> toResponseCustom(shippingAddress, userMap.get(Math.toIntExact( shippingAddress.getUserId()))))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget ShippingAddressResponse response, @Context User user){
        response.setUserResponse(userMapper.toResponse(user));
    }
}
