package cl.laaraucana.simulacion.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.laaraucana.simulacion.utils.ConstantesFormalizar;
import cl.laaraucana.simulacion.utils.FormUtils;
import cl.laaraucana.simulacion.utils.RutUtil;

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
	private String tipoAfiliado;
	private String oficina;
	private String monto;
	private String cuotas;
	private String rutAfiliado;
	private String segCesantia;
	private String tasaMensual;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		tipoAfiliado = "";
		oficina = "";
		monto = "";
		cuotas = "";
		rutAfiliado = "";
		segCesantia = "";
		tasaMensual = "";

	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ((oficina.isEmpty())) {
			errors.add("oficina", new ActionMessage("errors.formalizar.required"));
		}
		if ((cuotas.isEmpty())) {
			errors.add("cuotas", new ActionMessage("errors.formalizar.required"));
		}

		if ((monto.isEmpty())) {
			errors.add("monto", new ActionMessage("errors.formalizar.required"));
		} else if (!FormUtils.isLong(monto)) {
			errors.add("monto", new ActionMessage("errors.formalizar.formato"));
		} else if (Long.parseLong(monto) < ConstantesFormalizar.MIN_SOCIAL) {
			errors.add("monto", new ActionMessage("errors.formalizar.monto.menorA", ConstantesFormalizar.MIN_SOCIAL));
		}

		if ((tipoAfiliado.isEmpty())) {
			errors.add("tipoAfiliado", new ActionMessage("errors.formalizar.required"));
		}

		if (!errors.isEmpty()) {
			//request.setAttribute("oficinasList", ConstantesFormalizar.getOficinas());
			request.setAttribute("afiliadosMap", ConstantesFormalizar.getTipoAfiliados());
		}

		if ((rutAfiliado == null) || (rutAfiliado.length() == 0)) {
			errors.add("rut", new ActionMessage("errors.rut.invalido"));
		} else if (!RutUtil.IsRutValido(rutAfiliado)) {
			errors.add("rut", new ActionMessage("errors.rut.invalido"));
		}

		return errors;
	}

	public String getTipoAfiliado() {
		return tipoAfiliado;
	}

	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getCuotas() {
		return cuotas;
	}

	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
	}

	public String getRutAfiliado() {
		return rutAfiliado;
	}

	public void setRutAfiliado(String rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}

	public String getSegCesantia() {
		return segCesantia;
	}

	public void setSegCesantia(String segCesantia) {
		this.segCesantia = segCesantia;
	}

	public String getTasaMensual() {
		return tasaMensual;
	}

	public void setTasaMensual(String tasaMensual) {
		this.tasaMensual = tasaMensual;
	}
	

}
