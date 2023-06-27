package cl.araucana.spring.services;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.araucana.spring.dao.GenericDao;
import cl.araucana.spring.entities.Formulario;

@Service
public class ClaveEmpresasServiceImpl implements ClaveEmpresasService{

	@Autowired
	GenericDao<Formulario> dao;
	
	@Override
	public void saveClaveEmpresa(Formulario form) {
		
		dao.save(form);
		
	}

	@Override
	public Formulario readFile(long id) {
		 
		
	   return dao.findById(Formulario.class, id);
		
	}
	
	

}
