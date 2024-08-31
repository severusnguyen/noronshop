package com.example.noronshopcommons.data.mapper.addressshop;

import com.example.noronshopcommons.data.dto.request.AddressShopRequest;
import com.example.noronshopcommons.data.dto.response.AddressShopResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.tables.pojos.AddressShop;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public abstract class AddressShopMapper extends BaseMap<AddressShopRequest, AddressShopResponse, AddressShop> {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "shopId", source = "id")
    })
    public abstract AddressShop toPojo(Shop shop);

}
