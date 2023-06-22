package cse.external.client.dao.impl;

import java.util.logging.Logger;

import cse.database.dao.exception.DAOException;
import cse.external.client.dao.CalificacionRiesgoExternaDAO;
import cse.model.businessobject.CalificacionRiesgoExterna;

public class CalificacionRiesgoExternaDAOMock implements CalificacionRiesgoExternaDAO {

	private static Logger logger = Logger.getLogger(CalificacionRiesgoExternaDAOMock.class.getName());
			
	public CalificacionRiesgoExterna findByRut(String solicitudID, String numero, String digitoChequeo) throws DAOException{
			logger.entering(this.getClass().getName(), "execute", new Object[]{numero, digitoChequeo});
		CalificacionRiesgoExterna externa = new CalificacionRiesgoExterna();
		externa.setNombre("Externa");
		externa.setDescripcion("Externa Descripcion");
		externa.setValor(new Integer(400));
		externa.setFecNac("1978-07-09");
		externa.setGenero("M");
		externa.setEstCivil("S");
		logger.exiting(this.getClass().getName(), "execute", externa);
		return externa;
	}

	public int getDefaultScoreByClasificacion(String clasifEmpresa)
			throws DAOException {
		clasifEmpresa = clasifEmpresa.trim().toUpperCase();
		if (clasifEmpresa.equals("SP")) {
			return 777;
		} else {
			return 449;
		}
	}

}
