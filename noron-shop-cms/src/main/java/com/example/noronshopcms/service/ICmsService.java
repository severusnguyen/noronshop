package com.example.noronshopcms.service;

import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopconfig.exception.ApiException;

public interface ICmsService<Rq, Rp, ID>{
    Rp insert(Rq request) throws ApiException;
    Rp update(ID id, Rq request) throws ApiException;
    Rp findById(ID id) throws ApiException;
    Rp deleteById(ID id) throws ApiException;
   Page<Rp> search(SearchRequest searchRequest);
}
