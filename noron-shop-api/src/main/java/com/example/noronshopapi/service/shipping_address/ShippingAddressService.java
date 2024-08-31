package com.example.noronshopapi.service.shipping_address;

import com.example.noronshopapi.service.AbsService;
import com.example.noronshopcommons.data.dto.request.ShippingAddressRequest;
import com.example.noronshopcommons.data.dto.response.ShippingAddressResponse;
import com.example.noronshopcommons.data.mapper.shipping_address.ShippingAddressMapper;
import com.example.noronshopcommons.data.tables.pojos.ShippingAddress;
import com.example.noronshoprepository.repository.shipping_repository.ShippingAddressRepository;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressService extends AbsService<ShippingAddressRequest, ShippingAddressResponse,
        ShippingAddress, Integer, ShippingAddressRepository, ShippingAddressMapper>  {

    public ShippingAddressService(ShippingAddressRepository repository, ShippingAddressMapper mapper){
        this.mapper = mapper;
        this.repository = repository;
    }
}
