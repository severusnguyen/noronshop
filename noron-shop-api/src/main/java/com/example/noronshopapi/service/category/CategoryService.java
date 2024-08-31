package com.example.noronshopapi.service.category;

import com.example.noronshopapi.service.AbsService;
import com.example.noronshopcommons.data.dto.request.CategoryRequest;
import com.example.noronshopcommons.data.dto.response.CategoryResponse;
import com.example.noronshopcommons.data.mapper.CategoryMapper;
import com.example.noronshopcommons.data.tables.pojos.Category;
import com.example.noronshoprepository.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends AbsService<CategoryRequest, CategoryResponse, Category, Integer, CategoryRepository, CategoryMapper> implements ICategoryService {

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.repository = categoryRepository;
        this.mapper = categoryMapper;
    }
}
