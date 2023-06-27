package com.alexis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.dao.UserDao;
import com.alexis.dao.rolDao;
import com.alexis.dto.RoleDto;
import com.alexis.model.Role;
import com.alexis.model.Usuario;

@Service("roleServices")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private rolDao dao;

	@Autowired
	private UserDao dao2;

	public List<Role> findAll() {

		List<Role> list = new ArrayList<Role>();

		try {
			list = new ArrayList<>();
			dao.findAll().iterator().forEachRemaining(list::add);
		} catch (Exception e) {

		}
		return list;
	}

	 

	public void save(Role entity) {

		dao.save(entity);

	}

	
	public void deleteByUserId(Long id) {

		dao.deleteByUserId(id);

	}


	public void deleteByIdNull() {
		
		dao.deleteByIdNull();
	}
	 
	
	 

}
