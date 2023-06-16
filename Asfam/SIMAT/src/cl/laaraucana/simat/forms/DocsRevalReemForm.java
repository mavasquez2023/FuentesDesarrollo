package cl.laaraucana.simat.forms;

//import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class DocsRevalReemForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id = 0;
	private String mes_informacion = "";
	private int codigo_entidad = 0;
	private int tiposubsidio = 0;
	private int mod_pago_original = 0;
	private String codigo_banco_original = "";
	private String serie_documento_original = "";
	private String num_documento_original = "";
	private String fecha_emision_documento_original = "0001-01-01";
	private long monto_documento_original = 0;
	private int estado_documento_original = 0;
	private int modo_pago_nuevo = 0;
	private String codigo_banco_nuevo = "";
	private String serie_documento_nuevo = "";
	private String num_documento_nuevo = "";
	private String fecha_emision_documento_nuevo = "0001-01-01";
	private long monto_documento_nuevo = 0;

	public long getMonto_documento_nuevo() {
		return monto_documento_nuevo;
	}

	public void setMonto_documento_nuevo(long monto_documento_nuevo) {
		this.monto_documento_nuevo = monto_documento_nuevo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEstado_documento_original() {
		return estado_documento_original;
	}

	public void setEstado_documento_original(int estado_documento_original) {
		this.estado_documento_original = estado_documento_original;
	}

	public String getFecha_emision_documento_nuevo() {
		return fecha_emision_documento_nuevo;
	}

	public void setFecha_emision_documento_nuevo(String fecha_emision_documento_nuevo) {
		this.fecha_emision_documento_nuevo = fecha_emision_documento_nuevo;
	}

	public String getFecha_emision_documento_original() {
		return fecha_emision_documento_original;
	}

	public void setFecha_emision_documento_original(String fecha_emision_documento_original) {
		this.fecha_emision_documento_original = fecha_emision_documento_original;
	}

	public int getModo_pago_nuevo() {
		return modo_pago_nuevo;
	}

	public void setModo_pago_nuevo(int modo_pago_nuevo) {
		this.modo_pago_nuevo = modo_pago_nuevo;
	}

	public int getMod_pago_original() {
		return mod_pago_original;
	}

	public void setMod_pago_original(int mod_pago_original) {
		this.mod_pago_original = mod_pago_original;
	}

	public long getMonto_documento_original() {
		return monto_documento_original;
	}

	public void setMonto_documento_original(long monto_documento_original) {
		this.monto_documento_original = monto_documento_original;
	}

	public String getNum_documento_nuevo() {
		return num_documento_nuevo;
	}

	public void setNum_documento_nuevo(String num_documento_nuevo) {
		this.num_documento_nuevo = num_documento_nuevo;
	}

	public String getNum_documento_original() {
		return num_documento_original;
	}

	public void setNum_documento_original(String num_documento_original) {
		this.num_documento_original = num_documento_original;
	}

	public String getSerie_documento_original() {
		return serie_documento_original;
	}

	public void setSerie_documento_original(String serie_documento_original) {
		this.serie_documento_original = serie_documento_original;
	}

	public String getSerie_documento_nuevo() {
		return serie_documento_nuevo;
	}

	public void setSerie_documento_nuevo(String serie_documento_nuevo) {
		this.serie_documento_nuevo = serie_documento_nuevo;
	}

	public int getCodigo_entidad() {
		return codigo_entidad;
	}

	public void setCodigo_entidad(int codigo_entidad) {
		this.codigo_entidad = codigo_entidad;
	}

	public String getMes_informacion() {
		return mes_informacion;
	}

	public void setMes_informacion(String mes_informacion) {
		this.mes_informacion = mes_informacion;
	}

	public int getTiposubsidio() {
		return tiposubsidio;
	}

	public void setTiposubsidio(int tiposubsidio) {
		this.tiposubsidio = tiposubsidio;
	}

	public String getCodigo_banco_nuevo() {
		return codigo_banco_nuevo;
	}

	public String getCodigo_banco_original() {
		return codigo_banco_original;
	}

	public void setCodigo_banco_nuevo(String codigo_banco_nuevo) {
		this.codigo_banco_nuevo = codigo_banco_nuevo;
	}

	public void setCodigo_banco_original(String codigo_banco_original) {
		this.codigo_banco_original = codigo_banco_original;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset field values here.

		id = 0;
		mes_informacion = "";
		codigo_entidad = 0;
		tiposubsidio = 0;
		mod_pago_original = 0;
		codigo_banco_original = "";
		serie_documento_original = "";
		num_documento_original = "";
		fecha_emision_documento_original = null;
		monto_documento_original = 0;
		estado_documento_original = 0;
		modo_pago_nuevo = 0;
		codigo_banco_nuevo = "";
		serie_documento_nuevo = "";
		num_documento_nuevo = "";
		fecha_emision_documento_nuevo = null;
		monto_documento_nuevo = 0;

	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		return errors;

	}
}
