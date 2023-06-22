package cse.database.dao;

import cse.database.dao.exception.DAOException;
import cse.model.businessobject.CalificacionRiesgoExterna;
import cse.model.businessobject.Rut;

public interface EquifaxCacheDAO {
	
	public CalificacionRiesgoExterna findByRut(String numero, String digitoChequeo) throws DAOException;

	public int insert(Rut rut, CalificacionRiesgoExterna datos) throws DAOException;
	
}
