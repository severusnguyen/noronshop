package com.example.noronshopapi.service.product;

import com.example.noronshopapi.service.IService;
import com.example.noronshopcommons.data.dto.request.ProductRequest;
import com.example.noronshopcommons.data.dto.response.ProductResponse;

public interface IProductService extends IService<ProductRequest, ProductResponse, Integer> {
}
