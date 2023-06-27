package com.alexis.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.alexis.model.Usuario;



public interface PagingAndSortingRepository
extends CrudRepository<Usuario, Long> {

Iterable<Usuario> findAll(Sort sort);

Page<Usuario> findAll(Pageable pageable);


}
