package cl.araucana.spl.forms.admin.inconsistencia;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionForm;

import cl.araucana.spl.beans.Estado;
import cl.araucana.spl.beans.Pago;

public class ModificarInconsistenciaForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private BigDecimal idPago;
	private Pago pago;
	private Estado estado;
	private String estadoId;
	private String estadoIdActual;
	private String observacion;
	private String pagado;
	private String pagadoActual;
	private String pagadoDesc;
	private List estados;
	private Boolean modificable;
	private List pagoPagados;
	
	/**
	 * @return the modificable
	 */
	public Boolean getModificable() {
		return modificable;
	}

	/**
	 * @param modificable the modificable to set
	 */
	public void setModificable(Boolean modificable) {
		this.modificable = modificable;
	}

	/**
	 * @return the estados
	 */
	public List getEstados() {
		return estados;
	}

	/**
	 * @param estados the estados to set
	 */
	public void setEstados(List estados) {
		this.estados = estados;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * Constructor
	 */
	public ModificarInconsistenciaForm() {
		this.estado = new Estado();
	}
	
	/**
	 * @return the idPago
	 */
	public BigDecimal getIdPago() {
		return idPago;
	}

	/**
	 * @param idPago the idPago to set
	 */
	public void setIdPago(BigDecimal idPago) {
		this.idPago = idPago;
	}

	/**
	 * @return the pago
	 */
	public Pago getPago() {
		return pago;
	}

	/**
	 * @param pago the pago to set
	 */
	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public List getPagoPagados() {
		return pagoPagados;
	}

	public void setPagoPagados(List pagoPagados) {
		this.pagoPagados = pagoPagados;
	}

	public String getPagado() {
		return pagado;
	}

	public void setPagado(String pagado) {
		this.pagado = pagado;
	}

	public String getPagadoDesc() {
		return pagadoDesc;
	}

	public void setPagadoDesc(String pagadoDesc) {
		this.pagadoDesc = pagadoDesc;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public String getEstadoIdActual() {
		return estadoIdActual;
	}

	public void setEstadoIdActual(String estadoIdActual) {
		this.estadoIdActual = estadoIdActual;
	}

	public String getPagadoActual() {
		return pagadoActual;
	}

	public void setPagadoActual(String pagadoActual) {
		this.pagadoActual = pagadoActual;
	}
}
