package cl.laaraucana.RentasPrevired.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.RentasPrevired.dao.GenericDao;
import cl.laaraucana.RentasPrevired.entities.ArchivoEntradaEntity;

@Service
public class ArchivoServiceImpl implements ArchivoService{

	@Autowired
	private GenericDao<ArchivoEntradaEntity> dao;
	
	@Override
	public void saveFile(ArchivoEntradaEntity archivo) {
		
		dao.save(archivo);
	}

	 
}
