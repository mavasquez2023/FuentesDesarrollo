package cl.laaraucana.RentasPrevired.services;

import java.util.List;

 

import cl.laaraucana.RentasPrevired.entities.PreviredArchivosEntity;

public interface PreviredProcesoService {
	
	public void saveProcess(PreviredArchivosEntity entity);
	
	public List<PreviredArchivosEntity> findAll();
	
	public List<PreviredArchivosEntity> findByError();
	
	public boolean existName(String name);

}
