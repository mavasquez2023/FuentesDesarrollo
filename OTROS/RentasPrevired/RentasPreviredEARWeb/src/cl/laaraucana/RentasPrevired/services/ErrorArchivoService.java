package cl.laaraucana.RentasPrevired.services;


import java.util.List;

import cl.laaraucana.RentasPrevired.entities.ArchivoErrorEntity;

public interface ErrorArchivoService {

	public void saveError(ArchivoErrorEntity entity);
	
	public List<ArchivoErrorEntity> getAll();

	public boolean existName(String nombre);
}
