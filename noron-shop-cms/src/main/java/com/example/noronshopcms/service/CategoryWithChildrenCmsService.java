package com.example.noronshopcms.service;

import com.example.noronshopcommons.data.dto.request.CategoryWithChildrenRequest;
import com.example.noronshopcommons.data.dto.response.CategoryWithChildrenResponse;
import com.example.noronshopcommons.data.mapper.CategoryWithChildrenMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.Category;
import com.example.noronshoprepository.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryWithChildrenCmsService extends AbsCmsService<CategoryWithChildrenRequest, CategoryWithChildrenResponse,
        Category, Integer, CategoryRepository, CategoryWithChildrenMapper>{

    public CategoryWithChildrenCmsService(CategoryRepository repository, CategoryWithChildrenMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<CategoryWithChildrenResponse> search(SearchRequest searchRequest) {
        List<Category> categoryList = repository.search(searchRequest);
        List<CategoryWithChildrenResponse> categoryWithChildrenResponseList = mapper.toResponses(categoryList);
        return new Page<CategoryWithChildrenResponse>().setItems(categoryWithChildrenResponseList);
    }
}
