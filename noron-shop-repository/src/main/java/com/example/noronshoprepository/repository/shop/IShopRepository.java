package com.example.noronshoprepository.repository.shop;


import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshoprepository.repository.IBaseRepository;

import java.util.List;

public interface IShopRepository extends IBaseRepository<Shop, Integer> {

    public List<Shop> getShopByIds(List<Integer> ids);
}
