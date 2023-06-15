package cl.laaraucana.simulacion.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import cl.laaraucana.simulacion.utils.RutUtil;
import cl.laaraucana.simulacion.webservices.client.DataServiceUtil;

/**
 * Validaciones de simulación 
 * @version 1.0
 * @author
 */
public class SimularAcuerdoForm extends ActionForm

{

	private static final long serialVersionUID = -1042863742320580833L;
	private String rutAfiliado;
	private String contrato;
	private String tipoAfiliado;
	private String cuotas;
	private String montoAbono;
	private String dctoCapital;
	private String nombreAfiliado;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.
		rutAfiliado="";
		contrato="";
		tipoAfiliado="";
		cuotas="";
		montoAbono="";
		dctoCapital="";
		nombreAfiliado="";
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		if ((rutAfiliado == null) || (rutAfiliado.length() == 0)) {
			errors.add("rut", new ActionMessage("errors.rut.invalido"));
		} else if (!RutUtil.IsRutValido(rutAfiliado)) {
			errors.add("rut", new ActionMessage("errors.rut.invalido"));
		}
		if ((contrato.isEmpty())) {
			errors.add("contrato", new ActionMessage("errors.formalizar.required"));
		}
		if ((tipoAfiliado.isEmpty())) {
			errors.add("tipoAfiliado", new ActionMessage("errors.formalizar.required"));
		}
		
		if ((cuotas.isEmpty())) {
			errors.add("cuotas", new ActionMessage("errors.formalizar.required"));
		}
		
		if (!errors.isEmpty()) {
			request.setAttribute("oficinasList", DataServiceUtil.getOficinas());
			//request.setAttribute("afiliadosMap", ConstantesFormalizar.getTipoAfiliados());
		}
				

		return errors;
	}
	
	
	/**
	 * @return the rutAfiliado
	 */
	public String getRutAfiliado() {
		return rutAfiliado;
	}

	/**
	 * @param rutAfiliado the rutAfiliado to set
	 */
	public void setRutAfiliado(String rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}

	/**
	 * @return the contrato
	 */
	public String getContrato() {
		return contrato;
	}

	/**
	 * @param contrato the contrato to set
	 */
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	/**
	 * @return the tipoAfiliado
	 */
	public String getTipoAfiliado() {
		return tipoAfiliado;
	}

	/**
	 * @param tipoAfiliado the tipoAfiliado to set
	 */
	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	/**
	 * @return the cuotas
	 */
	public String getCuotas() {
		return cuotas;
	}

	/**
	 * @param cuotas the cuotas to set
	 */
	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
	}


	/**
	 * @return the montoAbono
	 */
	public String getMontoAbono() {
		return montoAbono;
	}

	/**
	 * @param montoAbono the montoAbono to set
	 */
	public void setMontoAbono(String montoAbono) {
		this.montoAbono = montoAbono;
	}

	/**
	 * @return the dctoCapital
	 */
	public String getDctoCapital() {
		return dctoCapital;
	}

	/**
	 * @param dctoCapital the dctoCapital to set
	 */
	public void setDctoCapital(String dctoCapital) {
		this.dctoCapital = dctoCapital;
	}

	/**
	 * @return the nombreAfiliado
	 */
	public String getNombreAfiliado() {
		return nombreAfiliado;
	}

	/**
	 * @param nombreAfiliado the nombreAfiliado to set
	 */
	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}

	
	
	
}
