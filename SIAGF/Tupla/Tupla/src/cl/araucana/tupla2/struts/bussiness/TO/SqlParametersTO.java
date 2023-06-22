package cl.araucana.tupla2.struts.bussiness.TO;

import org.apache.struts.action.ActionForm;

public class SqlParametersTO extends ActionForm {

	private static final long serialVersionUID = -7600608787334862902L;

	public SqlParametersTO() {

	}

	private String esquemaorigen;
	private String esquemadestino;
	private String tablaorigen;
	private String tablatupla;
	private String tablatramo;
	private String tablacausante;
	private String tablabeneficiario;
	private String tablamarcarechazo;
	private String tabla011;
	private String tabla012;
	private String maxid;
	private String minid;
	private String tabladestino;
	private boolean useThread;
	private boolean recXml;
	private String[] accion;
	private String[] actualizar;
	private int minperiodo;
	private int maxperiodo;
	private String[] proceso;

	public String getEsquemadestino() {
		return esquemadestino;
	}

	public void setEsquemadestino(String esquemadestino) {
		this.esquemadestino = esquemadestino;
	}

	public String getEsquemaorigen() {
		return esquemaorigen;
	}

	public void setEsquemaorigen(String esquemaorigen) {
		this.esquemaorigen = esquemaorigen;
	}

	public String getTablaorigen() {
		return tablaorigen;
	}

	public void setTablaorigen(String tablaorigen) {
		this.tablaorigen = tablaorigen;
	}

	public String getTablatramo() {
		return tablatramo;
	}

	public void setTablatramo(String tablatramo) {
		this.tablatramo = tablatramo;
	}

	public String getTablatupla() {
		return tablatupla;
	}

	public void setTablatupla(String tablatupla) {
		this.tablatupla = tablatupla;
	}

	public String getMaxid() {
		return maxid;
	}

	public void setMaxid(String maxid) {
		this.maxid = maxid;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTabladestino() {
		return tabladestino;
	}

	public void setTabladestino(String tabladestino) {
		this.tabladestino = tabladestino;
	}

	/**
	 * @return the tablacausante
	 */
	public String getTablacausante() {
		return tablacausante;
	}

	/**
	 * @param tablacausante the tablacausante to set
	 */
	public void setTablacausante(String tablacausante) {
		this.tablacausante = tablacausante;
	}

	/**
	 * @return the tablabeneficiario
	 */
	public String getTablabeneficiario() {
		return tablabeneficiario;
	}

	/**
	 * @param tablabeneficiario the tablabeneficiario to set
	 */
	public void setTablabeneficiario(String tablabeneficiario) {
		this.tablabeneficiario = tablabeneficiario;
	}

	/**
	 * @return the tablamarcarechazo
	 */
	public String getTablamarcarechazo() {
		return tablamarcarechazo;
	}

	/**
	 * @param tablamarcarechazo the tablamarcarechazo to set
	 */
	public void setTablamarcarechazo(String tablamarcarechazo) {
		this.tablamarcarechazo = tablamarcarechazo;
	}

	/**
	 * @return the minid
	 */
	public String getMinid() {
		return minid;
	}

	/**
	 * @param minid the minid to set
	 */
	public void setMinid(String minid) {
		this.minid = minid;
	}

	/**
	 * @return the useThread
	 */
	public boolean isUseThread() {
		return useThread;
	}

	/**
	 * @param useThread the useThread to set
	 */
	public void setUseThread(boolean useThread) {
		this.useThread = useThread;
	}

	/**
	 * @return the recXml
	 */
	public boolean isRecXml() {
		return recXml;
	}

	/**
	 * @param recXml the recXml to set
	 */
	public void setRecXml(boolean recXml) {
		this.recXml = recXml;
	}

	/**
	 * @return the accion
	 */
	public String[] getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String[] accion) {
		this.accion = accion;
	}

	/**
	 * @return the actualizar
	 */
	public String[] getActualizar() {
		return actualizar;
	}

	/**
	 * @param actualizar the actualizar to set
	 */
	public void setActualizar(String[] actualizar) {
		this.actualizar = actualizar;
	}

	/**
	 * @return the tabla011
	 */
	public String getTabla011() {
		return tabla011;
	}

	/**
	 * @param tabla011 the tabla011 to set
	 */
	public void setTabla011(String tabla011) {
		this.tabla011 = tabla011;
	}

	/**
	 * @return the tabla012
	 */
	public String getTabla012() {
		return tabla012;
	}

	/**
	 * @param tabla012 the tabla012 to set
	 */
	public void setTabla012(String tabla012) {
		this.tabla012 = tabla012;
	}

	/**
	 * @return the minperiodo
	 */
	public int getMinperiodo() {
		return minperiodo;
	}

	/**
	 * @param minperiodo the minperiodo to set
	 */
	public void setMinperiodo(int minperiodo) {
		this.minperiodo = minperiodo;
	}

	/**
	 * @return the maxperiodo
	 */
	public int getMaxperiodo() {
		return maxperiodo;
	}

	/**
	 * @param maxperiodo the maxperiodo to set
	 */
	public void setMaxperiodo(int maxperiodo) {
		this.maxperiodo = maxperiodo;
	}

	/**
	 * @return the proceso
	 */
	public String[] getProceso() {
		return proceso;
	}

	/**
	 * @param proceso the proceso to set
	 */
	public void setProceso(String[] proceso) {
		this.proceso = proceso;
	}

	
	
}
