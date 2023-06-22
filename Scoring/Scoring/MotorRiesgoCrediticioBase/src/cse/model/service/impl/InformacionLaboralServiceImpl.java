package cse.model.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import cse.dao.factory.DAOFactory;
import cse.dao.factory.impl.DAOFactoryImpl;
import cse.database.dao.exception.DAOException;
import cse.legacy.connection.AS400ProgramExecutionException;
import cse.model.businessobject.InformacionLaboral;
import cse.model.businessobject.Rut;
import cse.model.service.InformacionLaboralService;
import cse.model.service.exception.InfoLaboralException;

public class InformacionLaboralServiceImpl implements InformacionLaboralService {

	private DAOFactory daoFactory;
	private static Logger logger = Logger.getLogger(InformacionLaboralServiceImpl.class.getName());
	
	public InformacionLaboralServiceImpl() {
		super();
		try {
			daoFactory = DAOFactoryImpl.getInstance();			
		} catch (DAOException e) {
			logger.log(Level.SEVERE, "Problemas al instanciar el servicio de Datos Demograficos");
			e.printStackTrace();
		}
	}

	
	
	public InformacionLaboral findInformacionLaboral(Rut rutEmpleado) throws InfoLaboralException {
		InformacionLaboral datos;
		try {
			datos = daoFactory.getInformacionLaboralDAO().findByRut(rutEmpleado.getNumero(), rutEmpleado.getDigitoChequeo());
		} catch (AS400ProgramExecutionException e) {
			logger.log(Level.SEVERE, "Problemas consiguiendo informacion laboral para: ", rutEmpleado );
			throw new InfoLaboralException("Problemas en la ejecucion del programa AS400", e); 
		}
		return datos;
	}

}
