package com.example.noronshoprepository.repository.product;

import com.example.noronshopcommons.data.tables.pojos.Product;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IProductRepository {
    List<Product> getProductByIds(List<Integer> id);

    List<Product> getListProductByIds(Collection<Integer> id);
}
