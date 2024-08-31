package com.example.noronshopcommons.data.mapper.violidation_info;

import com.example.noronshopcommons.data.dto.request.ViolationInfoRequest;
import com.example.noronshopcommons.data.dto.response.ViolationInfoResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.mapper.ProductMapper;
import com.example.noronshopcommons.data.mapper.shop.ShopMapper;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.data.tables.pojos.ViolationInfo;
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
public abstract class ViolationInfoMapper extends BaseMap<ViolationInfoRequest, ViolationInfoResponse, ViolationInfo> {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ShopMapper shopMapper;

    public abstract ViolationInfoResponse toResponseCustom(ViolationInfo violationInfo, @Context Product product, @Context Shop shop);

    public List<ViolationInfoResponse> toResponsesCustom(List<ViolationInfo> violationInfos, Map<Integer, Product> productMap, Map<Integer, Shop> shopMap){
        return violationInfos
                .stream()
                .map(violationInfo -> toResponseCustom(violationInfo, productMap.get(violationInfo.getProductId()), shopMap.get(violationInfo.getShopId())))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget ViolationInfoResponse response, @Context Product product, @Context Shop shop){
        response.setProductResponse(productMapper.toResponse(product));
        response.setShopResponse(shopMapper.toResponse(shop));
    }
}
