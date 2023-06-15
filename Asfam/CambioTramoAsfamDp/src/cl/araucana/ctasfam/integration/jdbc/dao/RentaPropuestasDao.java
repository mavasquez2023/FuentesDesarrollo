package cl.araucana.ctasfam.integration.jdbc.dao;

import java.util.List;

import cl.araucana.ctasfam.business.to.AceptacionPropuestaTO;
import cl.araucana.ctasfam.integration.jdbc.exception.RentaPropuestasException;

public interface RentaPropuestasDao extends GenericDao {
	public List select(int rutEmpresa, String dvEmpresa, int rutEncargado,
			String dvEncargado) throws RentaPropuestasException;
	
	public boolean insert(AceptacionPropuestaTO propuesta)
			throws RentaPropuestasException;
	
}