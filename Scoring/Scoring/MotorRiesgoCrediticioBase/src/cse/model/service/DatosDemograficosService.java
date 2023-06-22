package cse.model.service;

import cse.model.businessobject.DatosDemograficos;
import cse.model.businessobject.Rut;
import cse.model.service.exception.DemograficosException;

public interface DatosDemograficosService {
	
	public DatosDemograficos findDatosDemograficos(Rut rut, int tipoAfiliado) throws DemograficosException;

}
