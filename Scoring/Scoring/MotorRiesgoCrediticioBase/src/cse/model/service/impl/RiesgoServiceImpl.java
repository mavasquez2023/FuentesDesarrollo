package cse.model.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import cse.dao.factory.DAOFactory;
import cse.dao.factory.impl.DAOFactoryImpl;
import cse.database.dao.exception.DAOException;
import cse.database.dao.traza.TrazaCalificacionRiesgoExternaDAO;
import cse.legacy.connection.AS400ProgramExecutionException;
import cse.model.businessobject.CalificacionRiesgoExterna;
import cse.model.businessobject.ClasificacionRiesgoEmpleador;
import cse.model.businessobject.Rut;
import cse.model.service.RiesgoService;
import cse.model.service.exception.RiesgoEmpleadorException;
import cse.model.service.exception.RiesgoExternoException;

public class RiesgoServiceImpl implements RiesgoService {

	private DAOFactory daoFactory;
	private static Logger logger = Logger.getLogger(RiesgoServiceImpl.class.getName());

	public RiesgoServiceImpl() {
		super();
		try {
			daoFactory = DAOFactoryImpl.getInstance();
		} catch (DAOException e) {
			logger.log(Level.SEVERE, "Problemas al instanciar el servicio de Riesgo");
			e.printStackTrace();
		}
	}

	/**
	 * Esta funcion obtiene el puntaje de calificacion externa de Equifax. Primero
	 * lo intenta recurriendo al cache de Riesgo Externo en Equifax, y despues
	 * lo intenta recurriendo a la evaluacion online de Equifax (Servicio Web Platinum).
	 * Si no esta en cache (tabla de base de datos), entonces despues de obtener desde
	 * la fuente on-line, se graba en cache.
	 */
	public CalificacionRiesgoExterna getCalificacionRiesgoExterna(String newSolicitudID, Rut rutSolicitante, String clasifEmpresa)
			throws RiesgoExternoException {
		CalificacionRiesgoExterna datos;
		
		boolean cacheExitoso = false;
		boolean errorEquifax = false;
	
/*		System.setProperty("javax.net.ssl.keyStore", "C:/trustfiles/key.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "araucana");
		System.setProperty("user.timezone", "AGT");
		System.setProperty("javax.net.ssl.trustStore", "C:/trustfiles/trust.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "metricarts");*/
		
		System.setProperty("com.ibm.ssl.contextProvider","IBMJSSE2");
		// NOTE: Make the protocol below match what you put in step 6 (SSL) 
		System.setProperty("com.ibm.ssl.protocol","SSL");
		System.setProperty("com.ibm.ssl.keyStoreType","JKS");
		System.setProperty("com.ibm.ssl.keyStore","C:/trustfiles/key.jks");
		System.setProperty("com.ibm.ssl.keyStorePassword", "araucana");
		System.setProperty("com.ibm.ssl.trustStoreType","JKS");
		System.setProperty("com.ibm.ssl.trustStore","C:/trustfiles/trust.jks");
		System.setProperty("com.ibm.ssl.trustStorePassword","metricarts");
		 System.setProperty("javax.net.debug","true");
		
		try {
			datos = daoFactory.getCalificacionRiesgoExternaDAO().findByRut(newSolicitudID, 
					rutSolicitante.getNumero(), rutSolicitante.getDigitoChequeo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Buscamos los datos en el cache
		try {
			datos = daoFactory.getEquifaxCacheDAO().findByRut(rutSolicitante.getNumero(), rutSolicitante.getDigitoChequeo());
			if (null != datos) {
				cacheExitoso = true;
			}
		} catch (DAOException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE,
					"Problemas consiguiendo cache equifax para : "
					+ rutSolicitante + ". -" + e.getMessage());
			throw new RiesgoExternoException(
					"Problemas consiguiendo cache equifax para  "
							+ rutSolicitante + ". -" + e.getMessage(), e.getCause());
		}
		// Alternativamente, buscamos los datos en Equifax
		try {
			if (null == datos) {
				//throw new DAOException("Simulacion de caida de servicio equifax");
				datos = daoFactory.getCalificacionRiesgoExternaDAO().findByRut(newSolicitudID, 
						rutSolicitante.getNumero(), rutSolicitante.getDigitoChequeo());
			}
		} catch (DAOException e) {
			logger.log(Level.SEVERE,
					"Problemas consiguiendo la clasificacion de riesgo externa para : "
					+ rutSolicitante + ". -" + e.getMessage());
			// En caso de que el acceso a Equifax no prospere, supondremos el
			// peor escenario y entregamos puntaje por defecto.
			datos = new CalificacionRiesgoExterna();
			
			try {
				Integer score = new Integer(daoFactory.getCalificacionRiesgoExternaDAO().getDefaultScoreByClasificacion(clasifEmpresa));
				datos.setValor(score);
			} catch (Exception e2) {
				throw new RiesgoExternoException(e2.getMessage());
			}
			errorEquifax = true;
		}
	
		//==== PARA EFECTOS DE TRAZABILIDAD
		String origenDatos = null;
		if (cacheExitoso){
			origenDatos = "Tabla Cache [RiesgoExterno] ";
		} else {
			if (errorEquifax){
				//origenDatos = "Se asignó el puntaje maximo posible [1] como último recurso";
				origenDatos = "Se asignó el puntaje por defecto según clasificación empresa como último recurso";
			} else {
				origenDatos = "Equifax Web Service";
			}	
		}
		
		try {
			TrazaCalificacionRiesgoExternaDAO trazaDAO = daoFactory.getTrazaCalificacionRiesgoExternaDAO();
			trazaDAO.insert(newSolicitudID, rutSolicitante, origenDatos, datos);
		} catch (DAOException daoEx) {
			logger.log(Level.SEVERE,
					"Problemas grabando la traza de clasificacion de riesgo externa para la solicitud "
					+ newSolicitudID + ". -" + daoEx.getMessage());
			throw new RiesgoExternoException(
					"Problemas grabando la traza de clasificacion de riesgo externa para la solicitud "
					+ newSolicitudID + ". -" + daoEx.getMessage());
		}
		//============ FIN TRAZA ==
		
		//Finalmente, insertamos los datos en el cache para las siguientes condiciones:
		// 1) No habia datos en el cache
		// 2) La conexión de Equifax fue exitosa
		try {
			if ((null != datos) && !cacheExitoso && !errorEquifax) {

				daoFactory.getEquifaxCacheDAO().insert(rutSolicitante, datos);
			}
		} catch (DAOException e) {
			logger.log(Level.SEVERE,
					"Problemas grabando la clasificacion de riesgo externa en tabla cache para : "
					+ rutSolicitante + ". -" + e.getMessage());
			throw new RiesgoExternoException(
					"Problemas grabando la clasificacion de riesgo externa en tabla cache para : "
							+ rutSolicitante + ". -" + e.getMessage(), e.getCause());
		}
		return datos;
	}

	public ClasificacionRiesgoEmpleador getClasificacionRiesgoEmpleador(String newSolicitudID, Rut rutEmpleador,
			int tipoAfiliado) throws RiesgoEmpleadorException {
		ClasificacionRiesgoEmpleador datos;
		try {
			datos = daoFactory.getClasificacionRiesgoDAO().findByRut(rutEmpleador.getNumero(),
					rutEmpleador.getDigitoChequeo(), tipoAfiliado);
		} catch (AS400ProgramExecutionException e) {
			logger.log(Level.SEVERE,
					"Problemas consiguiendo la clasificacion de riesgo del empleador para : "
							+ rutEmpleador + ". - " + e.getMessage());
			throw new RiesgoEmpleadorException(
					"Problemas consiguiendo la clasificacion de riesgo del empleador para : "
							+ rutEmpleador + ". - " + e.getMessage(), e.getCause());
		}
		return datos;
	}

}
