package cl.araucana.ctasfam.business.to;

import org.apache.struts.action.ActionForm;

public class AceptaPropuestaForm extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1409713527872680636L;
	public AceptaPropuestaForm(){
		
	}
	
	private String opcion;
	private String propuesta;
	private String rutt;
	private String serviceName;
	private String proceso;
	private String etapa;
	private String ruts;
	
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	public String getPropuesta() {
		return propuesta;
	}
	public void setPropuesta(String propuesta) {
		this.propuesta = propuesta;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getRutt() {
		return rutt;
	}
	public void setRutt(String rutt) {
		this.rutt = rutt;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getRuts() {
		return ruts;
	}
	public void setRuts(String ruts) {
		this.ruts = ruts;
	}
	
	
	
	
	

}
