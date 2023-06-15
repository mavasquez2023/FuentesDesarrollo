package cl.araucana.ctasfam.batch.dao;

import java.util.List;

import cl.araucana.ctasfam.batch.common.dto.PropiedadConfiguracionDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;

public interface ConfiguracionAppDao {
	public List<PropiedadConfiguracionDto> getAllPropiedadesDeConfiguracion() throws TechnicalException;
}
