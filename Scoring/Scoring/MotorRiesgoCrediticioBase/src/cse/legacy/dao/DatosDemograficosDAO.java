package cse.legacy.dao;

import cse.legacy.connection.AS400ProgramExecutionException;
import cse.model.businessobject.DatosDemograficos;

public interface DatosDemograficosDAO {

	DatosDemograficos findByRut(String numero, String digitoChequeo) throws AS400ProgramExecutionException;
	
}
