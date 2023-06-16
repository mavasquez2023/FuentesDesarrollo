package cl.araucana.ctasfam.batch.dao;

import cl.araucana.ctasfam.batch.common.dto.CantidadPropuestasAfiliadoDto;
import cl.araucana.ctasfam.batch.common.dto.PropuestaAfiliadoDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;

public interface PropuestaAfiliadoDao {
	public CantidadPropuestasAfiliadoDto getCantidadPropuestaByAfiliado(Integer periodo, Integer rutEmpresa, Integer rutAfiliado) throws TechnicalException;
	
	public Boolean insertPropuestaAfiliado(PropuestaAfiliadoDto propAfil) throws TechnicalException;
	
	public Boolean updatePropuestaAfiliado(PropuestaAfiliadoDto propAfil) throws TechnicalException;
}
