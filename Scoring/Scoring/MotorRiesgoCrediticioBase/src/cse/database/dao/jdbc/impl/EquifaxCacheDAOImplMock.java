package cse.database.dao.jdbc.impl;

import cse.database.dao.EquifaxCacheDAO;
import cse.database.dao.exception.DAOException;
import cse.model.businessobject.CalificacionRiesgoExterna;
import cse.model.businessobject.Rut;

public class EquifaxCacheDAOImplMock implements EquifaxCacheDAO{

	public CalificacionRiesgoExterna findByRut(String numero, String digitoChequeo)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public int insert(Rut rut, CalificacionRiesgoExterna datos) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}
}
