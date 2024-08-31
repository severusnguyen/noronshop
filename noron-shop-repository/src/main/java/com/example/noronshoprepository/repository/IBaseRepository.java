package com.example.noronshoprepository.repository;

import com.example.noronshopcommons.data.modal.SearchRequest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IBaseRepository <P, ID>{
    Optional<P> insertReturning(P pojo);
    Integer update(P pojo, ID id);
    Integer delete(ID id);
    Optional<P> findById(ID id);
    List<Integer> insert(Collection<P> pojos);
    List<P> getAll();
    List<P> search(SearchRequest searchRequest);

}
