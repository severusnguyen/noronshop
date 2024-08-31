package com.example.noronshopcms.service;

import com.example.noronshopcommons.data.dto.request.CategoryRequest;
import com.example.noronshopcommons.data.dto.response.CategoryResponse;
import com.example.noronshopcommons.data.mapper.CategoryMapper;
import com.example.noronshopcommons.data.tables.pojos.Category;
import com.example.noronshoprepository.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryCmsService extends AbsCmsService<CategoryRequest, CategoryResponse, Category, Integer,
        CategoryRepository, CategoryMapper>{

    public CategoryCmsService(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
