package com.alexis.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.dao.ContactDao;
import com.alexis.model.Contacto;

@Service(value="contactoService")
@Transactional
public class ContactoServiceImpl implements ContactoService{

	
	@Autowired
	private ContactDao dao;
	
	
	public void save(Contacto entity) {
		
		dao.save(entity);
		
	}

	
	public void delete(long id) {
		
		dao.deleteById(id);
		
	}

	
	public void update(Contacto entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	
	public List<Contacto> findAll() {
		// TODO Auto-generated method stub
		List<Contacto> list = new ArrayList<>();
		dao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}


	public Contacto findById(long id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

}
