package com.example.noronshopcommons.data.mapper.book_info;

import com.example.noronshopcommons.config.JsonMapper;
import com.example.noronshopcommons.data.dto.request.BookInfoRequest;
import com.example.noronshopcommons.data.dto.request.ProductRequest;
import com.example.noronshopcommons.data.dto.response.BookInfoResponse;
import com.example.noronshopcommons.data.dto.response.ProductResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.mapper.ProductMapper;
import com.example.noronshopcommons.data.modal.Image;
import com.example.noronshopcommons.data.modal.Info;
import com.example.noronshopcommons.data.tables.pojos.BookInfo;
import com.example.noronshopcommons.data.tables.pojos.Category;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.utils.CollectionUtils;
import lombok.SneakyThrows;
import org.jooq.JSON;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class BookInfoMapper extends BaseMap<BookInfoRequest, BookInfoResponse, BookInfo> {

    @Autowired
    ProductMapper productMapper;

    @Override
    @IterableMapping(qualifiedByName = "toResponse")
    public abstract List<BookInfoResponse> toResponses(List<BookInfo> pojos);

    public abstract BookInfoResponse toResponseCustom(BookInfo pojo, @Context Product product);

    public List<BookInfoResponse> toResponsesCustom(List<BookInfo> bookInfos, Map<Integer, Product> productMap) {
        return bookInfos.stream()
                .map(bookInfo -> toResponseCustom(bookInfo, productMap.get(bookInfo.getProductId())))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget BookInfoResponse bookInfoResponse, @Context Product category) {
        bookInfoResponse.setProductResponse(productMapper.toResponse(category));
    }

}
