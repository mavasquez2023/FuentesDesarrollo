package cl.araucana.tupla2.struts.bussiness.TO;

import org.apache.struts.action.ActionForm;

public class ActualizacionTO extends ActionForm {
	private String esquemaorigen;
	private String esquemadestino;
	private String tablaorigen;
	private String tabladestino;
	private String tablatramo;
	private String tablamarcarechazo;
	private String tabla011;
	private String tabla012;
	private String[] accion;
	private String[] actualizar;
	private boolean isForm;
	private int minperiodo;
	private int maxperiodo;
	private String[] proceso;
	
	public boolean isForm() {
		return isForm;
	}

	public void setForm(boolean isForm) {
		this.isForm = isForm;
	}

	public ActualizacionTO() {

	}

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

	public String getTabladestino() {
		return tabladestino;
	}

	public void setTabladestino(String tabladestino) {
		this.tabladestino = tabladestino;
	}

	public String getTablaorigen() {
		return tablaorigen;
	}

	public void setTablaorigen(String tablaorigen) {
		this.tablaorigen = tablaorigen;
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
	 * @return the tablatramo
	 */
	public String getTablatramo() {
		return tablatramo;
	}

	/**
	 * @param tablatramo the tablatramo to set
	 */
	public void setTablatramo(String tablatramo) {
		this.tablatramo = tablatramo;
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
