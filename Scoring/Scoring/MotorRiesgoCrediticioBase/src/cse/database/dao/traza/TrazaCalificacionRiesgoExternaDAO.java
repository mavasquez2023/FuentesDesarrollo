package cse.database.dao.traza;

import cse.database.dao.exception.DAOException;
import cse.model.businessobject.CalificacionRiesgoExterna;
import cse.model.businessobject.Rut;

public interface TrazaCalificacionRiesgoExternaDAO {

	public int insert(String newSolicitudID, Rut rutSolicitante,
			String origenDatos, CalificacionRiesgoExterna datos) throws DAOException;

}
