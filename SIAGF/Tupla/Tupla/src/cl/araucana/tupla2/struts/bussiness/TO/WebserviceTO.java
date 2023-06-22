package cl.araucana.tupla2.struts.bussiness.TO;

import org.apache.struts.action.ActionForm;

public class WebserviceTO extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 875117849187900036L;

	public WebserviceTO() {

	}

	private String esquemaorigen;
	private String esquemadestino;
	private String tablaorigen;
	private String tablatuplas;
	private String tablatramos;
	private String tablacausante;
	private String tablabeneficiario;
	private String maxid;
	private String minid;
	private String tabladestino;
	private String useThread;
	private String recxml;

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public String getTablaorigen() {
		return tablaorigen;
	}

	public void setTablaorigen(String tablaorigen) {
		this.tablaorigen = tablaorigen;
	}

	public String getTablatramos() {
		return tablatramos;
	}

	public void setTablatramos(String tablatramos) {
		this.tablatramos = tablatramos;
	}

	public String getTablatuplas() {
		return tablatuplas;
	}

	public void setTablatuplas(String tablatuplas) {
		this.tablatuplas = tablatuplas;
	}

	public String getMaxid() {
		return maxid;
	}

	public void setMaxid(String maxid) {
		this.maxid = maxid;
	}

	public static long getSerialversionuid() {
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
	 * @return the recxml
	 */
	public String getRecxml() {
		return recxml;
	}

	/**
	 * @param recxml the recxml to set
	 */
	public void setRecxml(String recxml) {
		this.recxml = recxml;
	}

	/**
	 * @return the useThread
	 */
	public String getUseThread() {
		return useThread;
	}

	/**
	 * @param useThread the useThread to set
	 */
	public void setUseThread(String useThread) {
		this.useThread = useThread;
	}
	
	
	
}
