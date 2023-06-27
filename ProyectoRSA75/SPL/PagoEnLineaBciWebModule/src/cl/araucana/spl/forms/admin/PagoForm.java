package cl.araucana.spl.forms.admin;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class PagoForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private BigDecimal pago;
	private List folios;
	private List eventos;

	public BigDecimal getPago() {
		return pago;
	}

	public void setPago(BigDecimal pago) {
		this.pago = pago;
	}

	public List getFolios() {
		return folios;
	}

	public void setFolios(List folios) {
		this.folios = folios;
	}

	public List getEventos() {
		return eventos;
	}

	public void setEventos(List eventos) {
		this.eventos = eventos;
	}
}
