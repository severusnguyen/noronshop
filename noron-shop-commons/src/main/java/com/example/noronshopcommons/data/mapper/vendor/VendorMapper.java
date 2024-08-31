package com.example.noronshopcommons.data.mapper.vendor;

import com.example.noronshopcommons.data.dto.request.VendorRequest;
import com.example.noronshopcommons.data.dto.response.VendorResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.data.tables.pojos.Vendor;
import com.example.noronshopcommons.data.mapper.shop.ShopMapper;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.A;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class VendorMapper extends BaseMap<VendorRequest, VendorResponse, Vendor> {

    @Autowired
    ShopMapper shopMapper;

    public abstract VendorResponse toResponseCustom(Vendor pojo, @Context Shop shop);

    public List<VendorResponse> toResponsesCustom(List<Vendor> vendors, Map<Integer, Shop> shopMap){
        return vendors.stream()
                .map(vendor -> toResponseCustom(vendor, shopMap.get(vendor.getShopId())))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget VendorResponse vendorResponse, @Context Shop shop){
        vendorResponse.setShopResponse(shopMapper.toResponse(shop));
    }
}
