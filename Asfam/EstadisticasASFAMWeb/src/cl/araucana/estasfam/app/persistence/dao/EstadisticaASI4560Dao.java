package cl.araucana.estasfam.app.persistence.dao;

import java.util.Date;
import java.util.List;

import cl.araucana.estasfam.app.business.model.ActividadEconomicaDTO;
import cl.araucana.estasfam.app.business.model.CargasPagosDirectoDTO;
import cl.araucana.estasfam.app.business.model.RegionDTO;
import cl.araucana.estasfam.common.exceptions.DaoException;

public interface EstadisticaASI4560Dao {
	public List<CargasPagosDirectoDTO> getCargasPagosDirectosPorRegion(Date mesEstadistica, Date fecGeneracion, Date fecDesde, Date fecHasta) throws DaoException;
	
	public List<ActividadEconomicaDTO> getActividades() throws DaoException;
	
}
