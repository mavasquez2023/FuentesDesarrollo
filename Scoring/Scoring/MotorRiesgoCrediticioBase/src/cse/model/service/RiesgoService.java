package cse.model.service;

import cse.model.businessobject.CalificacionRiesgoExterna;
import cse.model.businessobject.ClasificacionRiesgoEmpleador;
import cse.model.businessobject.Rut;
import cse.model.service.exception.RiesgoEmpleadorException;
import cse.model.service.exception.RiesgoExternoException;

public interface RiesgoService {
	
	public CalificacionRiesgoExterna getCalificacionRiesgoExterna(String newSolicitudID, Rut rutSolicitante, String clasifEmpresa) throws RiesgoExternoException;

	public ClasificacionRiesgoEmpleador getClasificacionRiesgoEmpleador(String newSolicitudID, Rut rutEmpleador, int tipoAfiliado) throws RiesgoEmpleadorException;
	

}
