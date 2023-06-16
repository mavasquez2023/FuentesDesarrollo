package cl.araucana.tupla2.struts.bussiness.TO;

import org.apache.struts.action.ActionForm;

public class ValidacionTO extends ActionForm {

	public ValidacionTO() {

	}

	private String esquemaorigen;
	private String esquemadestino;
	private String tablaorigen;
	private String tabladestino;
	private String maxid;

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

	public String getMaxid() {
		return maxid;
	}

	public void setMaxid(String maxid) {
		this.maxid = maxid;
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

}
