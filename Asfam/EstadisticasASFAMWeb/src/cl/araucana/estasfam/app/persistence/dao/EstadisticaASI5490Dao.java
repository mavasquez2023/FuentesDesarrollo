package cl.araucana.estasfam.app.persistence.dao;

import java.util.Date;
import java.util.List;

import cl.araucana.estasfam.app.business.model.CargasPorTipoDTO;
import cl.araucana.estasfam.app.business.model.PagosDirectoPorTipoDTO;
import cl.araucana.estasfam.common.exceptions.DaoException;

public interface EstadisticaASI5490Dao {
	public List<CargasPorTipoDTO> getCargasMesConPago(Date mesEstadistica) throws DaoException;
	
	public List<CargasPorTipoDTO> getCargasAtrasadasConPago(Date mesEstadistica) throws DaoException;
	
	public List<CargasPorTipoDTO> getCargasMesSinPago(Date mesEstadistica) throws DaoException;
	
	public List<CargasPorTipoDTO> getCargasAtrasadasSinPago(Date mesEstadistica) throws DaoException;
	
	public List<PagosDirectoPorTipoDTO> getPagosDirectosConPago(Date fecGeneracion, Date fecDesde, Date fecHasta) throws DaoException ;
}
