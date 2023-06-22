package cse.model.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import cse.dao.factory.DAOFactory;
import cse.dao.factory.impl.DAOFactoryImpl;
import cse.database.dao.exception.DAOException;
import cse.legacy.connection.AS400ProgramExecutionException;
import cse.model.businessobject.DatosDemograficos;
import cse.model.businessobject.Rut;
import cse.model.service.DatosDemograficosService;
import cse.model.service.exception.DemograficosException;


public class DatosDemograficosServiceImpl implements DatosDemograficosService {

	private DAOFactory daoFactory;
	private static Logger logger = Logger.getLogger(DatosDemograficosServiceImpl.class.getName());  
	
	public DatosDemograficosServiceImpl(){
		try {
			daoFactory = DAOFactoryImpl.getInstance();
		} catch (DAOException e) {
			logger.log(Level.SEVERE, "Problemas al instanciar el servicio de Datos Demograficos");
			e.printStackTrace();
		}
		 
	}
	
	public DatosDemograficos findDatosDemograficos(Rut rut, int tipoAfiliado) throws DemograficosException {
		logger.entering(this.getClass().getName(),"findDatosDemograficos", rut);
		DatosDemograficos datos;
		try {
			datos = daoFactory.getDatosDemograficosDAO().findByRut(rut.getNumero(), rut.getDigitoChequeo());
		} catch (AS400ProgramExecutionException e) {
			logger.log(Level.SEVERE, "Problemas consiguiendo Datos Demograficos para: ", rut );
			throw new DemograficosException("Problemas en la ejecucion del programa AS400 para : "+ rut, e); 
		}
		logger.exiting(this.getClass().getName(),"findDatosDemograficos", datos);
		return datos;
	}

	



}
