package cl.araucana.lme.liq.util;

import java.util.HashMap;
import java.util.Map;

import cl.araucana.lme.liq.svc.IAS400Svc_LIQ;
import cl.araucana.lme.liq.svc.SvcFactory_LIQ;

public class EndPointUtilLiq {

	/**
	 * atributos de que se utilizan para los endpoint
	 */
	private static EndPointUtilLiq instancia = null;
	private static Map endPointsDbMap = new HashMap();
	private static Map endPointsPropertiesMap = new HashMap();
	public static Map endPointsCompletoMap = new HashMap();
	private static Map erroresPorOperadorMap = new HashMap();
	public String operadorImed = "";
	public String operadorMediPass = "";
	public String prioridadDb = "";
	public String prioridadProperties = "";
	public static boolean estado=true;

	private EndPointUtilLiq() {

	}

	/**
	 * Instancia que carga los parametros de endpoint
	 * @return
	 * @throws Exception
	 */
	public static EndPointUtilLiq getInstance() throws Exception {
		if (instancia == null) {
			instancia = new EndPointUtilLiq();
			instancia.cargarParametros();
		}
		return instancia;
	}

	/**
	 * carga los distintos parametros que se utilizan en los endpoint
	 * @throws Exception
	 */
	public void cargarParametros() throws Exception {
		cargarParametrosGenerales();
		cargarParametrosDb();
		cargarParametrosProperties();
		unirDatosHash();
		reCargarParametrosErrores();
	}

	/**
	 * ejecuta el dao que obtiene los endpoint desde la base de datos.
	 * @throws Exception
	 */
	private void cargarParametrosDb() throws Exception {
		endPointsDbMap = new HashMap();
		IAS400Svc_LIQ svc_a = SvcFactory_LIQ.getAS400Svc_LME();
		endPointsDbMap = svc_a.getEndPoints();
	}

	/**
	 * carga un HashMap con los endPoint que se obtienen desde los archivos properties.
	 */
	private void cargarParametrosProperties() {
		endPointsPropertiesMap = new HashMap();
		endPointsPropertiesMap.put(operadorMediPass + ",DETALLE," + prioridadProperties, ConfiguracionesLiq.getConfig("medipass.DETALLE"));
		endPointsPropertiesMap.put(operadorMediPass + ",ZONA_C," + prioridadProperties, ConfiguracionesLiq.getConfig("medipass.ZONA_C"));
		endPointsPropertiesMap.put(operadorImed + ",DETALLE," + prioridadProperties, ConfiguracionesLiq.getConfig("imed.DETALLE"));
		endPointsPropertiesMap.put(operadorImed + ",ZONA_C," + prioridadProperties, ConfiguracionesLiq.getConfig("imed.ZONA_C"));
	}

	/**
	 * une los datos de los endpoint de la base de datos con la de los properties y se diferencias por la prioridad
	 */
	private void unirDatosHash() {
		endPointsCompletoMap = new HashMap();
		endPointsCompletoMap.putAll(endPointsDbMap);
		endPointsCompletoMap.putAll(endPointsPropertiesMap);
	}

	private void cargarParametrosGenerales() {
		operadorMediPass = ConfiguracionesLiq.getConfig("codigo.medipass");
		operadorImed = ConfiguracionesLiq.getConfig("codigo.imed");
		prioridadDb = ConfiguracionesLiq.getConfig("prioridad.db");
		prioridadProperties = ConfiguracionesLiq.getConfig("prioridad.properties");
	}

	/**
	 * Carga los parametros de errores segun los operadores en un HashMap
	 */
	public void reCargarParametrosErrores() {
		erroresPorOperadorMap = new HashMap();
		erroresPorOperadorMap.put(operadorImed + "," + prioridadDb, Boolean.FALSE);
		erroresPorOperadorMap.put(operadorImed + "," + prioridadProperties, Boolean.FALSE);
		erroresPorOperadorMap.put(operadorMediPass + "," + prioridadDb, Boolean.FALSE);
		erroresPorOperadorMap.put(operadorMediPass + "," + prioridadProperties, Boolean.FALSE);
		erroresPorOperadorMap.put(operadorImed, Boolean.FALSE);
		erroresPorOperadorMap.put(operadorMediPass, Boolean.FALSE);
	}

	/**
	 * obtiene el endpoint segun el codigo operador, nombre y prioridad. 
	 * @param codigoOpe
	 * @param nombre
	 * @param prioridad
	 * @return
	 */
	public String getEndPoint(String codigoOpe, String nombre, String prioridad) {
		return (String) endPointsCompletoMap.get(codigoOpe + "," + nombre + "," + prioridad);
	}

	/**
	 * obtiene el valor booleano del error segun el codigo de operador y la prioridad
	 * @param codigoOpe
	 * @param prioridad
	 * @return
	 */
	public Boolean getEstadoError(String codigoOpe, String prioridad) {
		return (Boolean) erroresPorOperadorMap.get(codigoOpe + "," + prioridad);
	}

	/**
	 * obtiene el valor booleano del error segun el codigo de operador
	 * @param codigoOpe
	 * @return
	 */
	public Boolean getEstadoError(String codigoOpe) {
		return (Boolean) erroresPorOperadorMap.get(codigoOpe);
	}

	/**
	 * obtiene el valor booleano del error para todos los operadores
	 * @return
	 */
	public boolean getEstadoErrorTodos() {
		return Boolean.TRUE == erroresPorOperadorMap.get(operadorMediPass) && Boolean.TRUE == erroresPorOperadorMap.get(operadorImed);
	}

	/**
	 * se realiza el cambio del error para el codigo de operador 
	 * @param codigoOpe
	 * @param estado
	 */
	public void cambiarEstadoError(String codigoOpe, Boolean estado) {
		erroresPorOperadorMap.put(codigoOpe, estado);
	}

	/**
	 * se realiza el cambio del error para el codigo de operador y la prioridad
	 * @param codigoOpe
	 * @param prioridad
	 * @param estado
	 */
	public void cambiarEstadoError(String codigoOpe, String prioridad, Boolean estado) {
		erroresPorOperadorMap.put(codigoOpe + "," + prioridad, estado);
	}

	/**
	 * Se obtiene el nombre del operador.
	 * @param codigoOpe
	 * @return
	 */
	public String getNombreOperador(String codigoOpe) {
		String salida = "prioridad 2";
		if (operadorImed.equals(codigoOpe)) {
			salida = "IMED";
		} else if (operadorMediPass.equals(codigoOpe)) {
			salida = "MEDIPASS";
		}
		return salida;
	}

	public static void setInstancia(EndPointUtilLiq instancia) {
		EndPointUtilLiq.instancia = instancia;
	}

	public static void setEndPointsDbMap(Map endPointsDbMap) {
		EndPointUtilLiq.endPointsDbMap = endPointsDbMap;
	}

	public static void setEndPointsCompletoMap(Map endPointsCompletoMap) {
		EndPointUtilLiq.endPointsCompletoMap = endPointsCompletoMap;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
