package com.example.noronshopcommons.data.mapper.tiki_products_price;

import com.example.noronshopcommons.data.dto.request.TikiProductPriceRequest;
import com.example.noronshopcommons.data.dto.response.TikiProductPriceResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.mapper.ProductMapper;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.data.tables.pojos.TikiProductPrice;
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
public abstract class TikiProductPriceMapper extends BaseMap<TikiProductPriceRequest, TikiProductPriceResponse, TikiProductPrice> {

    @Autowired
    ProductMapper productMapper;

    public abstract TikiProductPriceResponse toResponseCustom(TikiProductPrice tikiProductPrice, @Context Product product);

    public List<TikiProductPriceResponse> toResponsesCustom(List<TikiProductPrice> tikiProductPrices, Map<Integer, Product> productMap){
        return tikiProductPrices.stream()
                .map(tikiProductPrice -> toResponseCustom(tikiProductPrice, productMap.get(tikiProductPrice.getProductId())))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget TikiProductPriceResponse response, @Context Product product){
        response.setProductResponse(productMapper.toResponse(product));
    }
}
