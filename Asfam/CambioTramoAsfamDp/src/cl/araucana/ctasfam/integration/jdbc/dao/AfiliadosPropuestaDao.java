package cl.araucana.ctasfam.integration.jdbc.dao;

import java.util.List;

import cl.araucana.ctasfam.business.to.AfiliadosPropuestaTO;

public interface AfiliadosPropuestaDao extends GenericDao {

	public List select(String tipoBusqueda,
			AfiliadosPropuestaTO afiliadosPropuestaTO) throws Exception;

	public List selectPropuestaSaldos(AfiliadosPropuestaTO afiliadosPropuestaTO)
			throws Exception;

}
