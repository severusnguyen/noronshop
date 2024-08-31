package com.example.noronshopapi.service.shop;

import com.example.noronshopapi.service.AbsService;
import com.example.noronshopcommons.data.dto.request.ShopRequest;
import com.example.noronshopcommons.data.dto.response.ShopResponse;
import com.example.noronshopcommons.data.mapper.shop.ShopMapper;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshoprepository.repository.shop.ShopRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService extends AbsService<ShopRequest, ShopResponse, Shop, Integer, ShopRepositoryImpl, ShopMapper> implements IShopService {

    @Autowired
    public ShopService(ShopRepositoryImpl shopRepository, ShopMapper shopMapper) {
        this.repository = shopRepository;
        this.mapper = shopMapper;
    }

}
