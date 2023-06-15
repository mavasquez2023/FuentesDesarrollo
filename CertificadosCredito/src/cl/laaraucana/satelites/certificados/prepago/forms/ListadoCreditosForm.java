package cl.laaraucana.satelites.certificados.prepago.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.satelites.certificados.prepago.VO.CertificadoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoVO;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class ListadoCreditosForm extends ActionForm

{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7167475225818737193L;

	private String rut;
	private String[] prepago;
	private CertificadoPrepagoVO certificadoPrepago;
	List<SalidaCreditoPrepagoVO> listaCreditos;
	private String creditosPrepago;
	
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.rut = "";
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
/*
		if ( (this.rut == null) || (rut.length() == 0)) {
			errors.add("rut", new ActionMessage("error.rut.required"));
			return errors;
		}
		
		if(!RutUtil.IsRutValido(this.rut)){
			errors.add("rut", new ActionMessage("error.rut.formatoInvalido"));
			return errors;
		}
	*/	
		return errors;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}



	public String[] getPrepago() {
		return prepago;
	}

	public void setPrepago(String[] prepago) {
		this.prepago = prepago;
	}

	public CertificadoPrepagoVO getCertificadoPrepago() {
		return certificadoPrepago;
	}

	public void setCertificadoPrepago(CertificadoPrepagoVO certificadoPrepago) {
		this.certificadoPrepago = certificadoPrepago;
	}

	public List<SalidaCreditoPrepagoVO> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(List<SalidaCreditoPrepagoVO> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}

	public String getCreditosPrepago() {
		return creditosPrepago;
	}

	public void setCreditosPrepago(String creditosPrepago) {
		this.creditosPrepago = creditosPrepago;
	}
	
}
