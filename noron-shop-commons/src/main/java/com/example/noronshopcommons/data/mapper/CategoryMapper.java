package com.example.noronshopcommons.data.mapper;

import com.example.noronshopcommons.data.dto.request.CategoryRequest;
import com.example.noronshopcommons.data.dto.response.CategoryResponse;
import com.example.noronshopcommons.data.tables.pojos.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper extends BaseMap<CategoryRequest, CategoryResponse, Category>{

}
