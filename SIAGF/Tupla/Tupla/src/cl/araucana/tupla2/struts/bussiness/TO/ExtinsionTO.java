package cl.araucana.tupla2.struts.bussiness.TO;

import org.apache.struts.action.ActionForm;

public class ExtinsionTO extends ActionForm {

	public ExtinsionTO() {

	}

	public String getEsquemadestino() {
		return esquemadestino;
	}

	public void setEsquemadestino(String esquemadestino) {
		this.esquemadestino = esquemadestino;
	}

	public String getTabladestino() {
		return tabladestino;
	}

	public void setTabladestino(String tabladestino) {
		this.tabladestino = tabladestino;
	}

	public String getMaxid() {
		return maxid;
	}

	public void setMaxid(String maxid) {
		this.maxid = maxid;
	}

	public boolean isForm() {
		return isForm;
	}

	public void setForm(boolean isForm) {
		this.isForm = isForm;
	}

	private String rutcausante;
	private String esquemaorigen;
	private String tablaorigen;

	private String esquemadestino;
	private String tabladestino;
	private String maxid;
	private boolean isForm;

	public String getRutcausante() {
		return rutcausante;
	}

	public void setRutcausante(String rutcausante) {
		this.rutcausante = rutcausante;
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

}
