package cl.laaraucana.copagocredito.services;

import java.util.List;

import cl.laaraucana.copagocredito.entities.MandatosEntity;

public interface MandatoService {
	
	public List<MandatosEntity> findMandatoByRut(long rut) throws Exception;

}
