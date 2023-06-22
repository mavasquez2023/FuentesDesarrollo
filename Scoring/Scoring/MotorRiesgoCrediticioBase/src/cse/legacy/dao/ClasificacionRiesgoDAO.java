package cse.legacy.dao;

import cse.legacy.connection.AS400ProgramExecutionException;
import cse.model.businessobject.ClasificacionRiesgoEmpleador;


public interface ClasificacionRiesgoDAO {

	ClasificacionRiesgoEmpleador findByRut(String numero, String digitoChequeo, int tipoAfiliado) throws AS400ProgramExecutionException;
	
}
