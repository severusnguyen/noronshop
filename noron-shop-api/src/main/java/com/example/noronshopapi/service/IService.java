package com.example.noronshopapi.service;

import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;

import java.util.List;

public interface IService <Rq, Rp, ID>{
    Rp insert(Rq request);
    String insert(List<Rq> requestList);
    Rp update(ID id, Rq request);
    Rp findById(ID id);
    Page<Rp> search(SearchRequest searchRequest);
    Rp delete(ID id);
}
