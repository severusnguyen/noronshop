package com.example.noronshopcommons.data.mapper.tiki_products;

import com.example.noronshopcommons.config.JsonMapper;
import com.example.noronshopcommons.data.dto.request.ProductRequest;
import com.example.noronshopcommons.data.dto.request.TikiProductsRequest;
import com.example.noronshopcommons.data.dto.response.TikiProductsResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.mapper.ProductMapper;
import com.example.noronshopcommons.data.modal.Author;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.data.tables.pojos.TikiProducts;
import com.example.noronshopcommons.utils.CollectionUtils;
import lombok.SneakyThrows;
import org.jooq.JSON;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class TikiProductsMapper extends BaseMap<TikiProductsRequest, TikiProductsResponse, TikiProducts> {

    @Override
    @Mapping(target = "specifications", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "categories", ignore = true)
    public abstract TikiProducts toPOJO(TikiProductsRequest request) ;

    @Override
    @Mapping(target = "specifications", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "categories", ignore = true)
    public abstract List<TikiProducts> toPOJOs(List<TikiProductsRequest> requests) ;

    @Override
    @Mapping(target = "specifications", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "categories", ignore = true)
    public abstract TikiProductsResponse toResponse(TikiProducts pojo);

    @Override
    @Mapping(target = "specifications", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "categories", ignore = true)
    public abstract List<TikiProductsResponse> toResponses(List<TikiProducts> pojos);

    @Autowired
    ProductMapper productMapper;

    @Mapping(target = "specifications", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "categories", ignore = true)
    public abstract TikiProductsResponse toResponseCustom(TikiProducts tikiProducts, @Context Product product);

    @Mapping(target = "specifications", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "categories", ignore = true)
    public List<TikiProductsResponse> toResponsesCustom(List<TikiProducts> tikiProducts, Map<Integer, Product> productMap){
        return tikiProducts.stream()
                .map(tikiProduct -> toResponseCustom(tikiProduct, productMap.get(tikiProduct.getProductId())))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @AfterMapping
    public void afterToPojo(@MappingTarget TikiProducts tikiProducts, TikiProductsRequest tikiProductsRequest){
        if (tikiProducts.getAuthors() != null){
            String author = JsonMapper.getObjectMapper().writeValueAsString(tikiProductsRequest.getAuthors());
            tikiProducts.setAuthors(JSON.valueOf(author));
        }
        if (tikiProductsRequest.getCategories() != null){
            String categories = JsonMapper.getObjectMapper().writeValueAsString(tikiProductsRequest.getCategories());
            tikiProducts.setCategories(JSON.valueOf(categories));
        }
        if (tikiProductsRequest.getSpecifications() != null){
            String specifications = JsonMapper.getObjectMapper().writeValueAsString(tikiProductsRequest.getSpecifications());
            tikiProducts.setSpecifications(JSON.valueOf(specifications));
        }
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget TikiProductsResponse tikiProductsResponse, @Context Product product){
        tikiProductsResponse.setProductResponse(productMapper.toResponse(product));
    }
}
