package com.example.noronshopcommons.data.mapper.comment;

import com.example.noronshopcommons.data.dto.request.CommentRequest;
import com.example.noronshopcommons.data.dto.response.CommentResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.mapper.ProductMapper;
import com.example.noronshopcommons.data.tables.pojos.Comment;
import com.example.noronshopcommons.data.tables.pojos.Product;
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
public abstract class CommentMapper extends BaseMap<CommentRequest, CommentResponse, Comment> {

    @Autowired
    ProductMapper productMapper;

    public abstract CommentResponse toResponseCustom(Comment comment, @Context Product product);

    public List<CommentResponse> toResponsesCustom(List<Comment> comments, Map<Integer, Product> productMap){
        return comments.stream()
                .map(comment -> toResponseCustom(comment, productMap.get(comment.getProductId())))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget CommentResponse response, @Context Product product){
        response.setProductResponse(productMapper.toResponse(product));
    }
}
