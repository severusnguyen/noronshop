package com.example.noronshopcommons.data.mapper;

import com.example.noronshopcommons.config.JsonMapper;
import com.example.noronshopcommons.data.dto.request.ProductRequest;
import com.example.noronshopcommons.data.dto.response.ProductResponse;
import com.example.noronshopcommons.data.mapper.shop.ShopMapper;
import com.example.noronshopcommons.data.modal.Image;
import com.example.noronshopcommons.data.modal.Info;
import com.example.noronshopcommons.data.tables.pojos.Category;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.utils.CollectionUtils;
import lombok.SneakyThrows;
import org.jooq.JSON;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProductMapper extends BaseMap<ProductRequest, ProductResponse, Product>{

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Override
    @Named("toPOJO")
    @Mappings({
            @Mapping(target = "images", ignore = true),
            @Mapping(target = "info", ignore = true),
    })
    public abstract Product toPOJO(ProductRequest request);

    @IterableMapping(qualifiedByName = "toPOJO")
    public abstract List<Product> toPOJOs(List<ProductRequest> requests);

    @Override
    @Mappings({
            @Mapping(target = "images", ignore = true),
            @Mapping(target = "infos", ignore = true),
    })
    @Named("toResponse")
    public abstract ProductResponse toResponse(Product pojo);


    @Mappings({
            @Mapping(target = "images", ignore = true),
            @Mapping(target = "infos", ignore = true),
    })
    public abstract ProductResponse toResponseCustom(Product pojo, @Context Category category, @Context Shop shop);

    @IterableMapping(qualifiedByName = "toResponse")
    public abstract List<ProductResponse> toResponses(List<Product> product);



    public List<ProductResponse> toResponsesCustom(List<Product> product,
                                                      Map<Integer, Category> categoryMap,
                                                      Map<Integer, Shop> shopMap){
        return product.stream()
                .map(pd -> toResponseCustom(pd, categoryMap.get(pd.getCategoryId()), shopMap.get(pd.getShopId())))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @AfterMapping
    public void afterToPojo(@MappingTarget Product product, ProductRequest productRequest){
        if (productRequest.getImages() != null){
            String image = JsonMapper.getObjectMapper().writeValueAsString(productRequest.getImages());
            product.setImages(JSON.valueOf(image));
        }
        if (productRequest.getInfos() != null){
            String info = JsonMapper.getObjectMapper().writeValueAsString(productRequest.getInfos());
            product.setInfo(JSON.valueOf(info));
        }
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget ProductResponse productResponse, Product product,@Context Category category,@Context Shop shop){
        productResponse.setImages(CollectionUtils.getOrDefaultList(Image.class, product.getImages(), new ArrayList<>()));
        productResponse.setInfos(CollectionUtils.getOrDefaultList(Info.class, product.getInfo(), new ArrayList<>()));
        productResponse.setCategoryResponse(categoryMapper.toResponse(category));
        productResponse.setShopResponse(shopMapper.toResponse(shop));
    }

    public List<ProductResponse> toResponsesCustom(List<Product> products, Map<Integer, Shop> shopMap){
        return products.stream().map(product -> {
            ProductResponse productResponse = toResponse(product);
            productResponse.setShopResponse(shopMapper.toResponse(shopMap.get(product.getShopId())));
            return productResponse;
        }).collect(Collectors.toList());
    }
}
