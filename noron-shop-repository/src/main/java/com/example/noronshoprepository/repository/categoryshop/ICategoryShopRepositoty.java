package com.example.noronshoprepository.repository.categoryshop;

import com.example.noronshopcommons.data.tables.pojos.CategoryShop;
import com.example.noronshoprepository.repository.IBaseRepository;

import java.util.List;

public interface ICategoryShopRepositoty extends IBaseRepository<CategoryShop, Integer> {

    public List<CategoryShop> findByShopId(int shopId);
}
