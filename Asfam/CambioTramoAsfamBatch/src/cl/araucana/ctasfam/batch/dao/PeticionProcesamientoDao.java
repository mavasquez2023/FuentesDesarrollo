package cl.araucana.ctasfam.batch.dao;

import java.util.List;

import cl.araucana.ctasfam.batch.common.dto.PeticionProcesamientoDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;

public interface PeticionProcesamientoDao {

	public List<PeticionProcesamientoDto> getPeticionProcesamientoPorEstado(String estado, int numeroFilas, List<Integer> listRutsEmpEnCola, Integer maxIntentos) 
			throws TechnicalException;
	
	public Boolean updatePeticionProcesamiento(PeticionProcesamientoDto peticionProcesamiento) throws TechnicalException;
}
