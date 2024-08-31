package com.example.noronshopcms.service;

import com.example.noronshopconfig.exception.ApiException;

public interface IService <Rq, Rp, ID>{
    Rp insert(Rq request) throws ApiException;
    Rp update(ID id, Rq request);
    Rp findById(ID id);
}
