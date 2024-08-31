package com.example.noronshopcommons.data.mapper.reports_category;

import com.example.noronshopcommons.data.dto.request.ReportsCategoryRequest;
import com.example.noronshopcommons.data.dto.response.ReportsCategoryResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.mapper.shop.ShopMapper;
import com.example.noronshopcommons.data.tables.pojos.ReportsCategory;
import com.example.noronshopcommons.data.tables.pojos.Shop;
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
public abstract class ReportsCategoryMapper extends BaseMap<ReportsCategoryRequest, ReportsCategoryResponse, ReportsCategory> {

    @Autowired
    ShopMapper shopMapper;

    public abstract ReportsCategoryResponse toResponseCustom(ReportsCategory pojo, @Context Shop shop);

    public List<ReportsCategoryResponse> toResponsesCustom(List<ReportsCategory> reportsCategories, Map<Integer, Shop> shopMap){
        return reportsCategories.stream()
                .map(reportsCategory -> toResponseCustom(reportsCategory, shopMap.get(reportsCategory.getShopId())))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget ReportsCategoryResponse response, @Context Shop shop){
        response.setShopResponse(shopMapper.toResponse(shop));
    }
}
