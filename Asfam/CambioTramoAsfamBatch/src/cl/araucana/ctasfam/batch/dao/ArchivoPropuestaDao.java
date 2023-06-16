package cl.araucana.ctasfam.batch.dao;

import cl.araucana.ctasfam.batch.common.dto.ArchivoPropuestaDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;

public interface ArchivoPropuestaDao  {
	public ArchivoPropuestaDto getArchivoPropuesta(String pathAs400) throws TechnicalException;
}
