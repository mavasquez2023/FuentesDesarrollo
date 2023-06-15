package cl.araucana.ctasfam.batch.dao;

import cl.araucana.ctasfam.batch.common.dto.BitacoraProcesamientoDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;

public interface BitacoraProcesamientoDao {

	public Boolean insertBitacoraProcesamiento(BitacoraProcesamientoDto bitacoraProcesamiento) throws TechnicalException;
	
	public Boolean updateBitacoraProcesamiento(BitacoraProcesamientoDto bitacoraProcesamiento) throws TechnicalException;
}
