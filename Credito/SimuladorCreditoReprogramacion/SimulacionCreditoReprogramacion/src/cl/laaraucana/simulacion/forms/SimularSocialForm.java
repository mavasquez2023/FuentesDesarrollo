package cl.laaraucana.simulacion.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.laaraucana.simulacion.utils.ConstantesFormalizar;
import cl.laaraucana.simulacion.utils.FormUtils;
import cl.laaraucana.simulacion.utils.RutUtil;
import cl.laaraucana.simulacion.webservices.client.DataServiceUtil;

/**
 * Validaciones de simulación en calculadora
 * <ul>
 * <li>tipoTrabajador - [Tipo de afiliado: 1: Afiliado, 2:Pensionado]
 * <li>tipoProducto - [Credito especial, social, educacional]
 * <li>oficina - [Codigo de oficina]
 * <li>monto - [Monto solicitado]
 * <li>cuotas - [Cantidad de cuotas]
 * </ul>
 * 
 * @version 1.0
 * @author
 */
public class SimularSocialForm extends ActionForm

{

	private static final long serialVersionUID = -1042863742320580833L;
	private String rutAfiliado;
	private String contrato;
	private String tipoAfiliado;
	private String anexos;
	private String productos;
	private String cuotas;
	private String oficina;
	private String segCesantia;
	private String segDesgravamen;
	private String mesesGracia;
	private String dctoGravam;
	private String dctoGascob;
	private String dctoGasint;
	private String montoAbono;
	private String renta;
	private String insolvencia;
	private String cesantia;
	private String desgravamen;
	private String nombreAfiliado;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.
		rutAfiliado="";
		contrato="";
		tipoAfiliado="";
		anexos="";
		productos="";
		cuotas="";
		oficina="";
		segCesantia="";
		segDesgravamen="";
		mesesGracia="";
		dctoGravam="";
		dctoGascob="";
		dctoGasint="";
		montoAbono="";
		renta="";
		insolvencia="";
		cesantia="";
		desgravamen="";
		nombreAfiliado="";
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		if (rutAfiliado == null || (rutAfiliado.length() == 0)) {
			errors.add("rut", new ActionMessage("errors.rut.invalido"));
		} else if (!RutUtil.IsRutValido(rutAfiliado)) {
			errors.add("rut", new ActionMessage("errors.rut.invalido"));
		}
		if (contrato.isEmpty()) {
			errors.add("contrato", new ActionMessage("errors.formalizar.required"));
		}
		if (tipoAfiliado.isEmpty()) {
			errors.add("tipoAfiliado", new ActionMessage("errors.formalizar.required"));
		}
		if (anexos.isEmpty() && !tipoAfiliado.equals("DD")) {
			errors.add("anexos", new ActionMessage("errors.formalizar.required"));
		}
		if (productos.isEmpty()) {
			errors.add("productos", new ActionMessage("errors.formalizar.required"));
		}
		if (cuotas.isEmpty()) {
			errors.add("cuotas", new ActionMessage("errors.formalizar.required"));
		}
		if (mesesGracia.isEmpty()) {
			errors.add("mesesGracia", new ActionMessage("errors.formalizar.required"));
		}
		if (oficina.isEmpty()) {
			errors.add("oficina", new ActionMessage("errors.formalizar.required"));
		}
		if (renta.isEmpty() || (renta.equals("0") && !productos.equals("CA_REPRO_CESANTE"))) {
			errors.add("renta", new ActionMessage("errors.formalizar.required"));
		}
		if (montoAbono.isEmpty()) {
			montoAbono="0";
		}
		/*if (!errors.isEmpty()) {
			request.setAttribute("oficinasList", DataServiceUtil.getOficinas());
			//request.setAttribute("afiliadosMap", ConstantesFormalizar.getTipoAfiliados());
		}*/
		
		

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
	 * @return the anexos
	 */
	public String getAnexos() {
		return anexos;
	}

	/**
	 * @param anexos the anexos to set
	 */
	public void setAnexos(String anexos) {
		this.anexos = anexos;
	}

	/**
	 * @return the productos
	 */
	public String getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(String productos) {
		this.productos = productos;
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
	 * @return the oficina
	 */
	public String getOficina() {
		return oficina;
	}

	/**
	 * @param oficina the oficina to set
	 */
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	/**
	 * @return the segCesantia
	 */
	public String getSegCesantia() {
		return segCesantia;
	}

	/**
	 * @param segCesantia the segCesantia to set
	 */
	public void setSegCesantia(String segCesantia) {
		this.segCesantia = segCesantia;
	}

	/**
	 * @return the segDesgravamen
	 */
	public String getSegDesgravamen() {
		return segDesgravamen;
	}

	/**
	 * @param segDesgravamen the segDesgravamen to set
	 */
	public void setSegDesgravamen(String segDesgravamen) {
		this.segDesgravamen = segDesgravamen;
	}

	/**
	 * @return the mesesGracia
	 */
	public String getMesesGracia() {
		return mesesGracia;
	}

	/**
	 * @param mesesGracia the mesesGracia to set
	 */
	public void setMesesGracia(String mesesGracia) {
		this.mesesGracia = mesesGracia;
	}

	/**
	 * @return the dctoGravam
	 */
	public String getDctoGravam() {
		return dctoGravam;
	}

	/**
	 * @param dctoGravam the dctoGravam to set
	 */
	public void setDctoGravam(String dctoGravam) {
		this.dctoGravam = dctoGravam;
	}

	/**
	 * @return the dctoGascob
	 */
	public String getDctoGascob() {
		return dctoGascob;
	}

	/**
	 * @param dctoGascob the dctoGascob to set
	 */
	public void setDctoGascob(String dctoGascob) {
		this.dctoGascob = dctoGascob;
	}

	/**
	 * @return the dctoGasint
	 */
	public String getDctoGasint() {
		return dctoGasint;
	}

	/**
	 * @param dctoGasint the dctoGasint to set
	 */
	public void setDctoGasint(String dctoGasint) {
		this.dctoGasint = dctoGasint;
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
	 * @return the renta
	 */
	public String getRenta() {
		return renta;
	}

	/**
	 * @param renta the renta to set
	 */
	public void setRenta(String renta) {
		this.renta = renta;
	}

	/**
	 * @return the insolvencia
	 */
	public String getInsolvencia() {
		return insolvencia;
	}

	/**
	 * @param insolvencia the insolvencia to set
	 */
	public void setInsolvencia(String insolvencia) {
		this.insolvencia = insolvencia;
	}

	/**
	 * @return the cesantia
	 */
	public String getCesantia() {
		return cesantia;
	}

	/**
	 * @param cesantia the cesantia to set
	 */
	public void setCesantia(String cesantia) {
		this.cesantia = cesantia;
	}

	/**
	 * @return the desgravamen
	 */
	public String getDesgravamen() {
		return desgravamen;
	}

	/**
	 * @param desgravamen the desgravamen to set
	 */
	public void setDesgravamen(String desgravamen) {
		this.desgravamen = desgravamen;
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
