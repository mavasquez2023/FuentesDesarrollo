package cse.model.service.impl.helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cse.dao.factory.impl.DAOFactoryImpl;
import cse.database.dao.ClasificacionempresaDAO;
import cse.database.dao.CondicionotorgamientoDAO;
import cse.database.dao.EstadocivilDAO;
import cse.database.dao.SolicitudcondicionotorgamientoDAO;
import cse.database.dao.exception.DAOException;
import cse.database.dao.jdbc.JdbcConstants;
import cse.database.objects.Clasificacionempresa;
import cse.database.objects.Condicionotorgamiento;
import cse.database.objects.Estadocivil;
import cse.database.objects.Solicitudcondicionotorgamiento;
import cse.model.businessobject.CalificacionRiesgoExterna;
import cse.model.businessobject.ClasificacionRiesgoEmpleador;
import cse.model.businessobject.CondicionOtorgamiento;
import cse.model.businessobject.DatosDemograficos;
import cse.model.businessobject.InformacionLaboral;
import cse.model.businessobject.Rut;
import cse.model.exception.ScoringModuleException;
import cse.model.service.DatosDemograficosService;
import cse.model.service.InformacionLaboralService;
import cse.model.service.RiesgoService;
import cse.model.service.exception.DemograficosException;
import cse.model.service.exception.InfoLaboralException;
import cse.model.service.exception.RiesgoEmpleadorException;
import cse.model.service.exception.RiesgoExternoException;
import cse.model.service.impl.CondicionesOtorgamientoServiceImpl;
import cse.model.service.impl.DatosDemograficosServiceImpl;
import cse.model.service.impl.InformacionLaboralServiceImpl;
import cse.model.service.impl.RiesgoServiceImpl;

public class CondicionesOtorgamientoHelper {

	private static Logger logger = Logger.getLogger(CondicionesOtorgamientoServiceImpl.class
			.getName());
	
	public static List getCondiciones(String idSolicitud) throws DAOException {
		SolicitudcondicionotorgamientoDAO relationDAO = DAOFactoryImpl.getInstance().getSolicitudCondicionOtorgamientoDAO();
		List listCondicion = relationDAO.selectBySolicitud(idSolicitud);
		List condicionesAsList = new ArrayList();
		//ensamblar los business objects partir de los DTO
		for (Iterator iterator = listCondicion.iterator(); iterator.hasNext();) {
			Solicitudcondicionotorgamiento relationRecord = (Solicitudcondicionotorgamiento) iterator.next();			
			CondicionotorgamientoDAO condicionDAO = DAOFactoryImpl.getInstance().getCondicionOtorgamientoDAO();
			Condicionotorgamiento current = condicionDAO.selectByPrimaryKey(relationRecord.getIdcondicion());
			CondicionOtorgamiento businessObject = new CondicionOtorgamiento();
			businessObject.setNombre(current.getNombre());
			businessObject.setDescripcion(current.getDescripcion());				
			condicionesAsList.add(businessObject);
		}
		return condicionesAsList;
	}
	
	public static Integer translateEstadoCivil(DatosDemograficos demograficos) throws SQLException,
			DAOException {
		String nombre = demograficos.getEstadoCivil();
		return translateEstadoCivil(nombre);
	}

	public static Integer translateEstadoCivil(String nombre) throws DAOException, SQLException {
		
		// Equifax Rulezzzzzz!
		if (nombre == null) {
			return null;
		}
		
		EstadocivilDAO estadocivilDAO = DAOFactoryImpl.getInstance().getEstadoCivilDAO();
		List resultListCivil = estadocivilDAO.selectByNombre(nombre);
		if (resultListCivil.isEmpty()) {
//			throw new DAOException("No hay registros coincidentes con " + nombre + " en la tabla "
//					+ JdbcConstants.ESTADO_CIVIL_TABLE);
			return null;
		}
		if (resultListCivil.size() > 1) {
			throw new DAOException("Hay mas de un registro coincidente con " + nombre
					+ " en la tabla " + JdbcConstants.ESTADO_CIVIL_TABLE);
		}
		Estadocivil estadoCivil = (Estadocivil) resultListCivil.iterator().next();
		Integer idEstadoCivil = estadoCivil.getIdestadocivil();
		return idEstadoCivil;
	}
	
	//TODO: Limpiar esto aca. Debería estar atado a la BD
	public static Integer translateNroSueldos (String numero) throws DAOException {

		if (null == numero)
			throw new DAOException("Numero de sueldos viene vacio");

		try {

			Integer num = Integer.valueOf(numero);
			return num;

		} catch (NumberFormatException e) {
			// Entonces no era numero no mas
		}

		if (numero.startsWith("(+)"))
			return new Integer(11);
		else
			throw new DAOException("Numero sueldos no pudo ser traducido a integer desde motor");
	}

	public static Integer translateClasificacionEmpresa(ClasificacionRiesgoEmpleador riesgoEmpleador)
			throws SQLException, DAOException {
		String nombre = riesgoEmpleador.getNombre();
		return translateClasificacionRiesgoEmpresa(nombre);
	}

	public static Integer translateClasificacionRiesgoEmpresa(String nombre) throws DAOException,
			SQLException {
		ClasificacionempresaDAO clasificacionempresaDAO = DAOFactoryImpl.getInstance()
				.getClasificacionEmpresaDAO();
		List resultListEmpresa = clasificacionempresaDAO.selectByNombre(nombre);
		if (resultListEmpresa.isEmpty()) {
			throw new DAOException("Clasif. de Empresa incorrecta: '" + nombre + "' en la tabla "
					+ JdbcConstants.CLASIFICACION_EMPRESA_TABLE);
		}
		if (resultListEmpresa.size() > 1) {
			throw new DAOException("Hay mas de un registro coincidente con " + nombre
					+ " en la tabla " + JdbcConstants.CLASIFICACION_EMPRESA_TABLE);
		}
		Clasificacionempresa clasificacionEmpresa = (Clasificacionempresa) resultListEmpresa
				.iterator().next();
		Integer idClasificacionEmpresa = clasificacionEmpresa.getIdclasificacionempresa();
		return idClasificacionEmpresa;
	}

	public static CalificacionRiesgoExterna findRiesgoExterno(String newSolicitudID, Rut rutSolicitante, String clasifEmpresa)
			throws ScoringModuleException {
		logger.entering(CondicionesOtorgamientoHelper.class.getName(), "findRiesgoExterno", new Object[]{newSolicitudID, rutSolicitante});
		
		RiesgoService riesgoService = new RiesgoServiceImpl();
		CalificacionRiesgoExterna calificacionRiesgoExterna = null;
		
		try {
			calificacionRiesgoExterna = riesgoService.getCalificacionRiesgoExterna(newSolicitudID, rutSolicitante, clasifEmpresa);
		} catch (RiesgoExternoException e) { 
			logger.log(Level.SEVERE, "No hubo exito en la consulta a EQUIFAX: " + e.getMessage() , e.getCause());
		}
		
		logger.exiting(CondicionesOtorgamientoHelper.class.getName(), "findRiesgoExterno", calificacionRiesgoExterna);
		return calificacionRiesgoExterna;
	}

	public static InformacionLaboral findInfoLaboral(String newSolicitudID, Rut rutSolicitante, int tipoAfiliado) throws ScoringModuleException {
		logger.entering(CondicionesOtorgamientoHelper.class.getName(), "findInfoLaboral", new Object[]{newSolicitudID, rutSolicitante,new Integer(tipoAfiliado)});
		InformacionLaboralService laboralService = new InformacionLaboralServiceImpl();
		InformacionLaboral informacionLaboral = null;
		try {
			informacionLaboral = laboralService
					.findInformacionLaboral(rutSolicitante);
		} catch (InfoLaboralException e) {
			logger.log(Level.SEVERE, "No hubo exito en la consulta de informacion laboral : " + e.getMessage() , e.getCause());
			throw new ScoringModuleException("Problemas consiguiendo informacion laboral -  " + e.getMessage(), e.getCause());
		}
		logger.exiting(CondicionesOtorgamientoHelper.class.getName(), "findInfoLaboral", informacionLaboral);
		return informacionLaboral;
	}

	public static DatosDemograficos findDatosDemograficos(String newSolicitudID, Rut rutSolicitante, int tipoAfiliado)
			throws ScoringModuleException {
		logger.entering(CondicionesOtorgamientoHelper.class.getName(), "findDatosDemograficos", new Object[]{newSolicitudID, rutSolicitante,new Integer(tipoAfiliado)});
		DatosDemograficosService demograficosService = new DatosDemograficosServiceImpl();
		DatosDemograficos datosDemograficos = null;
		try {
			datosDemograficos = demograficosService
					.findDatosDemograficos(rutSolicitante, tipoAfiliado);
		} catch (DemograficosException e) {
			logger.log(Level.SEVERE, "No hubo exito en la consulta de datos demograficos: " + e.getMessage() , e.getCause());			
			throw new ScoringModuleException("Problemas consiguiendo informacion de datos demograficos -  " + e.getMessage(), e.getCause());
		}
		logger.exiting(CondicionesOtorgamientoHelper.class.getName(), "findDatosDemograficos", datosDemograficos);
		return datosDemograficos;
	}

	public static ClasificacionRiesgoEmpleador findRiesgoEmpleador(String newSolicitudID, Rut rutEmpleador, int tipoAfiliado)
			throws ScoringModuleException {
		logger.entering(CondicionesOtorgamientoHelper.class.getName(), "findRiesgoEmpleador", new Object[]{newSolicitudID, rutEmpleador,new Integer(tipoAfiliado)});
		RiesgoService riesgoService = new RiesgoServiceImpl();
		ClasificacionRiesgoEmpleador calificacionRiesgoEmpleador = null;
		try {
			calificacionRiesgoEmpleador = riesgoService
					.getClasificacionRiesgoEmpleador(newSolicitudID, rutEmpleador, tipoAfiliado);
		} catch (RiesgoEmpleadorException e) {
			logger.log(Level.SEVERE, "No hubo exito en la consulta de riesgo del empleador : " + e.getMessage() , e.getCause());
			throw new ScoringModuleException("Problemas consiguiendo informacion de riesgo empleador -  " + e.getMessage(), e.getCause());
		}
		logger.exiting(CondicionesOtorgamientoHelper.class.getName(), "findRiesgoEmpleador", calificacionRiesgoEmpleador);
		return calificacionRiesgoEmpleador;
	}

	
}
