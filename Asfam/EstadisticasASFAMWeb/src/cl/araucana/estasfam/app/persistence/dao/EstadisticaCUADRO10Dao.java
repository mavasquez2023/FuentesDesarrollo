package cl.araucana.estasfam.app.persistence.dao;

import java.util.Date;
import java.util.List;

import cl.araucana.estasfam.app.business.model.CargasPorColumnaDTO;
import cl.araucana.estasfam.common.exceptions.DaoException;

public interface EstadisticaCUADRO10Dao {

	public List<CargasPorColumnaDTO> getDatosCUADRO10(Date fecPeriodo) throws DaoException;
	
}
