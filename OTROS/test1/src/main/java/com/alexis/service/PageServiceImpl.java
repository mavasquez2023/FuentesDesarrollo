package com.alexis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alexis.dao.PagingAndSortingRepository;
import com.alexis.model.Usuario;

@Service
public class PageServiceImpl implements Pageservice{

	@Autowired
	private PagingAndSortingRepository dao;
	
	@SuppressWarnings("deprecation")
	@Override
	public Page<Usuario> findAllByPage(int skip, int take) {
		// TODO Auto-generated method stub
		return dao.findAll(new PageRequest(skip, take));
	}

}
