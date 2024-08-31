package com.example.noronshopcommons.data.mapper;

import com.example.noronshopcommons.data.dto.request.CategoryWithChildrenRequest;
import com.example.noronshopcommons.data.dto.response.CategoryResponse;
import com.example.noronshopcommons.data.dto.response.CategoryWithChildrenResponse;
import com.example.noronshopcommons.data.tables.pojos.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CategoryWithChildrenMapper extends BaseMap<CategoryWithChildrenRequest, CategoryWithChildrenResponse, Category>{

    public  CategoryWithChildrenResponse toResponse(Category category, Map<Integer, List<Category>> childMap){
        List<Category> children = childMap.getOrDefault(category.getId(), new ArrayList<>());
        List<CategoryWithChildrenResponse> childResponses = children.stream()
                .map(child -> toResponse(child, childMap))
                .collect(Collectors.toList());

        return new CategoryWithChildrenResponse(
                category.getId(),
                category.getName(),
                category.getParentId(),
                category.getImageSrc(),
                category.getImageTitle(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                category.getDeletedAt(),
                category.getCreatedBy(),
                category.getParentSlug(),
                category.getDescription(),
                category.getHandle(),
                category.getSortOrder(),
                childResponses
        );
    }

    public List<CategoryWithChildrenResponse> toResponses(List<Category> categoryList){
        Map<Integer, List<Category>> childMap = categoryList.stream()
                .filter(ctc -> ctc.getParentId() != null)
                .collect(Collectors.groupingBy(Category::getParentId));

        return categoryList.stream()
                .filter(ct -> ct.getParentId() == null)
                .map(ct -> toResponse(ct, childMap))
                .collect(Collectors.toList());
    }
}
