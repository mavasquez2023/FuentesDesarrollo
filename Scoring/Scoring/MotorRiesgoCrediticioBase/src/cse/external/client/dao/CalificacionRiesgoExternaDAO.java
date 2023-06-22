package cse.external.client.dao;

import cse.database.dao.exception.DAOException;
import cse.model.businessobject.CalificacionRiesgoExterna;

public interface CalificacionRiesgoExternaDAO {

	CalificacionRiesgoExterna findByRut(String solicitudID, String numero, String digitoChequeo) throws DAOException ;
	public int getDefaultScoreByClasificacion(String clasifEmpresa) throws DAOException;
}
