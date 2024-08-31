package com.example.noronshoprepository.repository.category_shop_product;

import com.example.noronshopcommons.data.tables.pojos.CategoryshopProduct;
import com.example.noronshoprepository.repository.IBaseRepository;
import org.jooq.Record2;
import org.jooq.Result;

import java.util.List;
import java.util.Optional;

public interface ICateShopProductRepo extends IBaseRepository<CategoryshopProduct, Integer> {

    public Result<Record2<Integer, Integer>> countProduct(List<Integer> idCategoryShop);

}
