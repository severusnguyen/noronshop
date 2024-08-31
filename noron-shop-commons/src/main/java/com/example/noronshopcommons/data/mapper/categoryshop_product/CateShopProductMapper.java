package com.example.noronshopcommons.data.mapper.categoryshop_product;

import com.example.noronshopcommons.data.dto.request.CateShopProductRequest;
import com.example.noronshopcommons.data.dto.response.CateShopProductResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.tables.pojos.CategoryshopProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CateShopProductMapper extends BaseMap<CateShopProductRequest, CateShopProductResponse, CategoryshopProduct> {
}
