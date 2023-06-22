package cse.model.service.impl;

import java.sql.SQLException;

import cse.dao.factory.DAOFactory;
import cse.dao.factory.impl.DAOFactoryImpl;
import cse.database.dao.SolicitudDAO;
import cse.database.dao.exception.DAOException;
import cse.model.businessobject.SolicitudServicio;
import cse.model.exception.ScoringModuleException;
import cse.model.service.SolicitudServicioService;


public class SolicitudServicioServiceImpl implements SolicitudServicioService {

	DAOFactory daoFactory;	
	
	public SolicitudServicioServiceImpl() throws ScoringModuleException {
		try {
			daoFactory = DAOFactoryImpl.getInstance();
		} catch (DAOException daoe) {
			daoe.printStackTrace();
			throw new ScoringModuleException("SolicitudServicioServiceImpl invocando un new DAOFactoryImpl()", daoe);
		}
		  
	}
	
	public SolicitudServicio findSolicitudServicio(String idSolicitudServicio) {
		try {
			SolicitudDAO solicitudDAO = daoFactory.getSolicitudDAO();
			solicitudDAO.selectByPrimaryKey(idSolicitudServicio);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String createSolicitudServicio() {		
		try {
			String idSolicitud = daoFactory.getSolicitudDAO().createNewSolicitud();
			return idSolicitud;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (DAOException daoe) {
			daoe.printStackTrace();
		}
		return null;
	}

}
