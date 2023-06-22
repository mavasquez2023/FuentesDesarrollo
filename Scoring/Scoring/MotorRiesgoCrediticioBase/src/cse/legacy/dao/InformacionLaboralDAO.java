package cse.legacy.dao;

import cse.legacy.connection.AS400ProgramExecutionException;
import cse.model.businessobject.InformacionLaboral;

public interface InformacionLaboralDAO {

	InformacionLaboral findByRut(String numero, String digitoChequeo) throws AS400ProgramExecutionException;
	
}
