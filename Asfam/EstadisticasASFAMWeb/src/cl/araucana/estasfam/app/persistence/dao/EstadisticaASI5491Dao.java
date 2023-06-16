package cl.araucana.estasfam.app.persistence.dao;

import java.util.Date;
import java.util.List;

import cl.araucana.estasfam.app.business.model.CargasPorColumnaDTO;
import cl.araucana.estasfam.app.business.model.CargasPorTipoDTO;
import cl.araucana.estasfam.app.business.model.PagosDirectoPorTipoDTO;
import cl.araucana.estasfam.common.exceptions.DaoException;

public interface EstadisticaASI5491Dao {
	public List<CargasPorColumnaDTO> getCargasAutorizadas(Date mesEstadistica) throws DaoException;
	
	public List<PagosDirectoPorTipoDTO> getPagosDirectosAutorizados(Date fecGeneracion, Date fecDesde, Date fecHasta) throws DaoException;
	
	public List<CargasPorTipoDTO> getSubsidiosVigenetes() throws DaoException;
	
}
