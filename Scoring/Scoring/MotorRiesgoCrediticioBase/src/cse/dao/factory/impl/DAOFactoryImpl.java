package cse.dao.factory.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.logging.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import cse.dao.factory.DAOFactory;
import cse.database.dao.AlertaSistemaDAO;
import cse.database.dao.ClasificacionempresaDAO;
import cse.database.dao.CondicionotorgamientoDAO;
import cse.database.dao.EquifaxCacheDAO;
import cse.database.dao.EstadocivilDAO;
import cse.database.dao.EvaluaCreditScoringPersonasDAO;
import cse.database.dao.SolicitudDAO;
import cse.database.dao.SolicitudcondicionotorgamientoDAO;
import cse.database.dao.exception.DAOException;
import cse.database.dao.ibatis.impl.ClasificacionempresaDAOImpl_IBatis;
import cse.database.dao.ibatis.impl.CondicionotorgamientoDAOImpl_IBatis;
import cse.database.dao.ibatis.impl.EstadocivilDAOImpl_IBatis;
import cse.database.dao.ibatis.impl.SolicitudDAOImpl_IBatis;
import cse.database.dao.ibatis.impl.SolicitudcondicionotorgamientoDAOImpl_IBatis;
import cse.database.dao.jdbc.impl.AlertaSistemaDAOImpl_Jdbc;
import cse.database.dao.jdbc.impl.ClasificacionempresaDAOImpl_Jdbc;
import cse.database.dao.jdbc.impl.EquifaxCacheDAOImplMock;
import cse.database.dao.jdbc.impl.EquifaxCacheDAOImpl_Jdbc;
import cse.database.dao.jdbc.impl.EstadocivilDAOImpl_Jdbc;
import cse.database.dao.jdbc.impl.EvaluaCreditScoringPersonasDAOImpl_Jdbc;
import cse.database.dao.jdbc.impl.SolicitudDAOImpl_Jdbc;
import cse.database.dao.traza.TrazaCalificacionRiesgoExternaDAO;
import cse.database.dao.traza.dao.jdbc.impl.TrazaCalificacionRiesgoExternaDAOImpl_Jdbc;
import cse.external.client.dao.CalificacionRiesgoExternaDAO;
import cse.external.client.dao.impl.CalificacionRiesgoExternaDAOImpl;
import cse.external.client.dao.impl.CalificacionRiesgoExternaDAOMock;
import cse.legacy.dao.ClasificacionRiesgoDAO;
import cse.legacy.dao.DatosDemograficosDAO;
import cse.legacy.dao.InformacionLaboralDAO;
import cse.legacy.dao.impl.ClasificacionRiesgoDAOImpl;
import cse.legacy.dao.impl.ClasificacionRiesgoDAOMock;
import cse.legacy.dao.impl.DatosDemograficosDAOImpl;
import cse.legacy.dao.impl.DatosDemograficosDAOMock;
import cse.legacy.dao.impl.InformacionLaboralDAOImpl;
import cse.legacy.dao.impl.InformacionLaboralDAOMock;
import cse.system.helper.PropertyLoader;

public class DAOFactoryImpl implements DAOFactory {

	private static final String IBATIS_XML_CONFIG_FILE = "sql-map-config.xml";

	private static final String MOCK_VALUE = "MOCK";
	private static final String REAL_VALUE = "REAL_AS400";
	private static final String JDBC_VALUE = "REAL_JDBC";
	private static final String IBATIS_VALUE = "REAL_IBATIS";
	private static final String EQUIFAX_VALUE = "REAL_EQUIFAX";

	private static Logger logger = Logger.getLogger(DAOFactoryImpl.class.getName());
	private static SqlMapClient sqlMap;
	private static DAOFactory instance;

	private String impl_CLASIFICACION_EMPRESA;

	private String impl_DATOS_DEMOGRAFICOS;

	private String impl_INFORMACION_LABORAL;

	private String impl_CLASIFICACION_RIESGO_EMPLEADOR;

	private String impl_CALIFICACION_RIESGO_EXTERNA;

	private String impl_ESTADO_CIVIL;

	private String impl_SOLICITUD;

	private DAOFactoryImpl() throws DAOException {
		String resource = IBATIS_XML_CONFIG_FILE;
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
			loadProperties();
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	private void loadProperties() {
		Properties prop = new Properties();
		prop = PropertyLoader.loadProperties("factory.properties");
		impl_DATOS_DEMOGRAFICOS = prop.getProperty("DATOS_DEMOGRAFICOS");
		impl_INFORMACION_LABORAL = prop.getProperty("INFORMACION_LABORAL");
		impl_CLASIFICACION_RIESGO_EMPLEADOR = prop.getProperty("CLASIFICACION_RIESGO_EMPLEADOR");
		impl_CLASIFICACION_EMPRESA = prop.getProperty("CLASIFICACION_EMPRESA");
		impl_CALIFICACION_RIESGO_EXTERNA = prop.getProperty("CALIFICACION_RIESGO_EXTERNA");
		impl_ESTADO_CIVIL = prop.getProperty("ESTADO_CIVIL");
		impl_SOLICITUD = prop.getProperty("SOLICITUD");
	}

	public static DAOFactory getInstance() throws DAOException {
		if (instance == null) {
			instance = new DAOFactoryImpl();
		}
		return instance;
	}

	public SolicitudDAO getSolicitudDAO() {
		logger.entering(this.getClass().getName(), "getSolicitudDAO");
		SolicitudDAO solicitudDAO = null;
		if (impl_SOLICITUD.equalsIgnoreCase(JDBC_VALUE)) {
			logger.info("Usando implementacion REAL JDBC para Solicitud");
			solicitudDAO = new SolicitudDAOImpl_Jdbc();
		} else if (impl_CLASIFICACION_EMPRESA.equalsIgnoreCase(IBATIS_VALUE)) {
			logger.info("Usando implementacion REAL IBATIS para Solicitud");
			solicitudDAO = new SolicitudDAOImpl_IBatis(sqlMap);
		} else {
			logger.warning("Problema de configuracion: Asigne un valor valido {MOCK | REAL_JDBC | REAL_IBATIS} para Solicitud");		
		}
		logger.exiting(this.getClass().getName(), "getSolicitudDAO", solicitudDAO);
		return solicitudDAO;
	}

	public DatosDemograficosDAO getDatosDemograficosDAO() {
		logger.entering(this.getClass().getName(), "getDatosDemograficosDAO");
		DatosDemograficosDAO datosDemograficosDAO = null;
		if (impl_DATOS_DEMOGRAFICOS.equalsIgnoreCase(REAL_VALUE)) {
			logger.info("Usando implementacion REAL para DatosDemograficos");
			datosDemograficosDAO = new DatosDemograficosDAOImpl();
		} else if (impl_DATOS_DEMOGRAFICOS.equalsIgnoreCase(MOCK_VALUE)) {
			logger.info("Usando implementacion MOCK para DatosDemograficos");
			datosDemograficosDAO = new DatosDemograficosDAOMock();
		} else {
			logger.warning("Problema de configuracion: Asigne un valor valido {MOCK | REAL_AS400} para DatosDemograficos");		
		}
		logger.exiting(this.getClass().getName(), "getDatosDemograficosDAO", datosDemograficosDAO);
		return datosDemograficosDAO;
	}

	public ClasificacionempresaDAO getClasificacionEmpresaDAO() {
		logger.entering(this.getClass().getName(), "getClasificacionEmpresaDAO");
		ClasificacionempresaDAO empresaDAO = null;
		if (impl_CLASIFICACION_EMPRESA.equalsIgnoreCase(JDBC_VALUE)) {
			logger.info("Usando implementacion REAL JDBC para ClasificacionEmpresa");
			empresaDAO = new ClasificacionempresaDAOImpl_Jdbc();
		} else if (impl_CLASIFICACION_EMPRESA.equalsIgnoreCase(IBATIS_VALUE)) {
			logger.info("Usando implementacion REAL IBATIS para ClasificacionEmpresa");
			empresaDAO = new ClasificacionempresaDAOImpl_IBatis(sqlMap);
		} else {
			logger.warning("Problema de configuracion: Asigne un valor valido {MOCK | REAL_JDBC | REAL_IBATIS} para ClasificacionEmpresa");
			// empresaDAO = new ClasificacionempresaDAOMock();
		}
		logger.exiting(this.getClass().getName(), "getClasificacionEmpresaDAO", empresaDAO);
		return empresaDAO;
	}

	public EstadocivilDAO getEstadoCivilDAO() {
		logger.entering(this.getClass().getName(), "getEstadoCivilDAO");
		EstadocivilDAO estadoCivilDAO = null;
		if (impl_ESTADO_CIVIL.equalsIgnoreCase(JDBC_VALUE)) {
			logger.info("Usando implementacion REAL JDBC para EstadoCivil");
			estadoCivilDAO = new EstadocivilDAOImpl_Jdbc();
		} else if (impl_CLASIFICACION_EMPRESA.equalsIgnoreCase(IBATIS_VALUE)) {
			logger.info("Usando implementacion REAL IBATIS para EstadoCivil");
			estadoCivilDAO = new EstadocivilDAOImpl_IBatis(sqlMap);
		} else {
			logger.warning("Problema de configuracion: Asigne un valor valido {MOCK | REAL_JDBC | REAL_IBATIS} para EstadoCivil");
		}
		logger.exiting(this.getClass().getName(), "getEstadoCivilDAO", estadoCivilDAO);
		return estadoCivilDAO;
	}

	public SolicitudcondicionotorgamientoDAO getSolicitudCondicionOtorgamientoDAO() {
		logger.entering(this.getClass().getName(), "getSolicitudCondicionOtorgamientoDAO");
		SolicitudcondicionotorgamientoDAO relacionDAO = new SolicitudcondicionotorgamientoDAOImpl_IBatis(
				sqlMap);
		logger.exiting(this.getClass().getName(), "getSolicitudCondicionOtorgamientoDAO",
				relacionDAO);
		return relacionDAO;
	}

	public CondicionotorgamientoDAO getCondicionOtorgamientoDAO() {
		logger.entering(this.getClass().getName(), "getCondicionOtorgamientoDAO");
		CondicionotorgamientoDAO condicionDAO = new CondicionotorgamientoDAOImpl_IBatis(sqlMap);
		logger.exiting(this.getClass().getName(), "getCondicionOtorgamientoDAO", condicionDAO);
		return condicionDAO;
	}

	public EvaluaCreditScoringPersonasDAO getEvaluaCreditScoringPersonasDAO() {
		logger.entering(this.getClass().getName(), "getEvaluaCreditScoringPersonasDAO");
		// EvaluaCreditScoringPersonasDAO scoringDAO = new
		// EvaluaCreditScoringPersonasDAOImpl_IBatis(sqlMap);
		EvaluaCreditScoringPersonasDAO scoringDAO = new EvaluaCreditScoringPersonasDAOImpl_Jdbc();
		logger.exiting(this.getClass().getName(), "getEvaluaCreditScoringPersonasDAO", scoringDAO);
		return scoringDAO;
	}

	public InformacionLaboralDAO getInformacionLaboralDAO() {
		logger.entering(this.getClass().getName(), "getInformacionLaboralDAO");
		InformacionLaboralDAO laboralDAO = null;
		if (impl_INFORMACION_LABORAL.equalsIgnoreCase(REAL_VALUE)) {
			logger.info("Se usara implementacion REAL para InformacionLaboral");
			laboralDAO = new InformacionLaboralDAOImpl();
		} else if (impl_INFORMACION_LABORAL.equalsIgnoreCase(MOCK_VALUE)) {
			logger.info("Se usara implementacion MOCK para InformacionLaboral");
			laboralDAO = new InformacionLaboralDAOMock();
		} else {
			logger.warning("Problema de configuracion: Asigne un valor valido {MOCK | REAL_AS400} para InformacionLaboral");			
		}
		logger.exiting(this.getClass().getName(), "getInformacionLaboralDAO", laboralDAO);
		return laboralDAO;
	}

	public ClasificacionRiesgoDAO getClasificacionRiesgoDAO() {
		logger.entering(this.getClass().getName(), "getClasificacionRiesgoDAO");
		ClasificacionRiesgoDAO riesgoDAO = null;
		if (impl_CLASIFICACION_RIESGO_EMPLEADOR.equalsIgnoreCase(REAL_VALUE)) {
			logger.info("Se usara implementacion REAL para ClasificacionRiesgoEmpleador");
			riesgoDAO = new ClasificacionRiesgoDAOImpl();
		} else if (impl_CLASIFICACION_RIESGO_EMPLEADOR.equalsIgnoreCase(MOCK_VALUE)) {
			logger.info("Se usara implementacion MOCK para ClasificacionRiesgoEmpleador");
			riesgoDAO = new ClasificacionRiesgoDAOMock();
		} else {
			logger.warning("Problema de configuracion: Asigne un valor valido {MOCK | REAL_AS400} para ClasificacionRiesgoEmpleador");		
		}
		logger.exiting(this.getClass().getName(), "getClasificacionRiesgoDAO", riesgoDAO);
		return riesgoDAO;
	}

	public CalificacionRiesgoExternaDAO getCalificacionRiesgoExternaDAO() {
		logger.entering(this.getClass().getName(), "getCalificacionRiesgoExternaDAO");
		CalificacionRiesgoExternaDAO externalDAO = null;		
		if (impl_CALIFICACION_RIESGO_EXTERNA.equalsIgnoreCase(EQUIFAX_VALUE)) {
			logger.info("Se usara implementacion REAL para CalificacionRiesgoExterna");
			externalDAO = new CalificacionRiesgoExternaDAOImpl();
		} else if (impl_CALIFICACION_RIESGO_EXTERNA.equalsIgnoreCase(MOCK_VALUE)) {
			logger.info("Se usara implementacion MOCK para CalificacionRiesgoExterna");
			externalDAO = new CalificacionRiesgoExternaDAOMock();
		} else {
			logger.warning("Problema de configuracion: Asigne un valor valido {MOCK | REAL_EQUIFAX} para CalificacionRiesgoExterna");			
		}
		logger.exiting(this.getClass().getName(), "getCalificacionRiesgoExternaDAO", externalDAO);
		return externalDAO;
	}

	public EquifaxCacheDAO getEquifaxCacheDAO() {
		logger.entering(this.getClass().getName(), "getEquifaxCacheDAO");
		EquifaxCacheDAO EquifaxDAO = null;
		
		if (impl_CALIFICACION_RIESGO_EXTERNA.equalsIgnoreCase(EQUIFAX_VALUE)) {
			
			logger.info("Se usara implementacion REAL para EquifaxCache");
			EquifaxDAO = new EquifaxCacheDAOImpl_Jdbc();
			
		} else if (impl_CALIFICACION_RIESGO_EXTERNA.equalsIgnoreCase(MOCK_VALUE)) {
			EquifaxDAO = new EquifaxCacheDAOImplMock();
			logger.info("Se usara implementacion MOCK para EquifaxCache");
			
		} else {
			logger.warning("Problema de configuracion: Asigne un valor valido {MOCK | REAL_EQUIFAX} para CalificacionRiesgoExterna");			
		}
		logger.exiting(this.getClass().getName(), "getEquifaxCacheDAO", EquifaxDAO);
		return EquifaxDAO;
	}

	public AlertaSistemaDAO getAlertaSistemaDAO(){
		logger.entering(this.getClass().getName(), "getAlertaSistemaDAO");
		AlertaSistemaDAO alertaSistemaDAO = new AlertaSistemaDAOImpl_Jdbc();
		logger.exiting(this.getClass().getName(), "getAlertaSistemaDAO", alertaSistemaDAO);
		return alertaSistemaDAO;
	}

	public TrazaCalificacionRiesgoExternaDAO getTrazaCalificacionRiesgoExternaDAO(){
		logger.entering(this.getClass().getName(), "getTrazaCalificacionRiesgoExternaDAO");
		TrazaCalificacionRiesgoExternaDAO trazaDAO = new TrazaCalificacionRiesgoExternaDAOImpl_Jdbc();
		logger.exiting(this.getClass().getName(), "getTrazaCalificacionRiesgoExternaDAO", trazaDAO);
		return trazaDAO;
	}
	

	
}