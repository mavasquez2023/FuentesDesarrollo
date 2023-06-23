package cl.araucana.spl.forms.admin.rendicion;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import cl.araucana.spl.beans.MedioPago;

public class ImportarRendicionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String idMedioPago;
	private FormFile rendicion;
	private List medios;
	
	private MedioPago medioPago = new MedioPago();
	
	private Integer importados;
	private Integer noImportados;
	private Integer consistentes;
	private Integer inconsistentes;
	private Integer totalDetallesRendicion;
	private List listaInconsistentes; //Lista detalles 
	private List listaInconsistentesPagos; //Lista pagos
	private List listaNoImportados;

	private String flagErrorCabeceraControl;
	

	/**
	 * @return the medioPago
	 */
	public MedioPago getMedioPago() {
		return medioPago;
	}

	/**
	 * @param medioPago the medioPago to set
	 */
	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	public ImportarRendicionForm() {
		//helloBean = new HelloWorldBean();
	}

	public String getIdMedioPago() {
		return idMedioPago;
	}

	public void setIdMedioPago(String idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	public List getMedios() {
		return medios;
	}

	public void setMedios(List medios) {
		this.medios = medios;
	}

	public FormFile getRendicion() {
		return rendicion;
	}

	public void setRendicion(FormFile rendicion) {
		this.rendicion = rendicion;
	}

	/**
	 * @return the listaInconsistentes
	 */
	public List getListaInconsistentes() {
		return listaInconsistentes;
	}

	/**
	 * @param listaInconsistentes the listaInconsistentes to set
	 */
	public void setListaInconsistentes(List listaInconsistentes) {
		this.listaInconsistentes = listaInconsistentes;
	}

	/**
	 * @return the listaNoImportados
	 */
	public List getListaNoImportados() {
		return listaNoImportados;
	}

	/**
	 * @param listaNoImportados the listaNoImportados to set
	 */
	public void setListaNoImportados(List listaNoImportados) {
		this.listaNoImportados = listaNoImportados;
	}

	/**
	 * @return the flagErrorCabeceraControl
	 */
	public String getFlagErrorCabeceraControl() {
		return flagErrorCabeceraControl;
	}

	/**
	 * @param flagErrorCabeceraControl the flagErrorCabeceraControl to set
	 */
	public void setFlagErrorCabeceraControl(String flagErrorCabeceraControl) {
		this.flagErrorCabeceraControl = flagErrorCabeceraControl;
	}

	/**
	 * @return the listaInconsistentesPagos
	 */
	public List getListaInconsistentesPagos() {
		return listaInconsistentesPagos;
	}

	/**
	 * @param listaInconsistentesPagos the listaInconsistentesPagos to set
	 */
	public void setListaInconsistentesPagos(List listaInconsistentesPagos) {
		this.listaInconsistentesPagos = listaInconsistentesPagos;
	}

	public Integer getConsistentes() {
		return consistentes;
	}

	public void setConsistentes(Integer consistentes) {
		this.consistentes = consistentes;
	}

	public Integer getImportados() {
		return importados;
	}

	public void setImportados(Integer importados) {
		this.importados = importados;
	}

	public Integer getInconsistentes() {
		return inconsistentes;
	}

	public void setInconsistentes(Integer inconsistentes) {
		this.inconsistentes = inconsistentes;
	}

	public Integer getNoImportados() {
		return noImportados;
	}

	public void setNoImportados(Integer noImportados) {
		this.noImportados = noImportados;
	}

	public Integer getTotalDetallesRendicion() {
		return totalDetallesRendicion;
	}

	public void setTotalDetallesRendicion(Integer totalDetallesRendicion) {
		this.totalDetallesRendicion = totalDetallesRendicion;
	}
	
}
