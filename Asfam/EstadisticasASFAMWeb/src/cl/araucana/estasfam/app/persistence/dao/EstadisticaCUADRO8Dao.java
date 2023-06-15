package cl.araucana.estasfam.app.persistence.dao;

import java.util.Date;
import java.util.List;

import cl.araucana.estasfam.app.business.model.AfiliadosPorTipoDTO;
import cl.araucana.estasfam.common.exceptions.DaoException;

public interface EstadisticaCUADRO8Dao {

	public List<AfiliadosPorTipoDTO> getDatosCUADRO8(Date fecPeriodo) throws DaoException;
	
}
