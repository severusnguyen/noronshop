package com.example.noronshopcms.service.category_shop;

import com.example.noronshopcms.service.IService;
import com.example.noronshopcommons.data.dto.request.CategoryShopRequest;
import com.example.noronshopcommons.data.dto.response.CategoryShopResponse;

import java.util.List;

public interface ICategoryShopService extends IService<CategoryShopRequest, CategoryShopResponse, Integer> {

    public List<CategoryShopResponse> findByShopId(int shopId);
}
