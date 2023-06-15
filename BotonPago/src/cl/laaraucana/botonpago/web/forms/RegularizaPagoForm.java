package cl.laaraucana.botonpago.web.forms;


import org.apache.struts.action.ActionForm;

public class RegularizaPagoForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 376267539353961894L;
	private String idPago = null;

	/**
	 * @return the idPago
	 */
	public String getIdPago() {
		return idPago;
	}

	/**
	 * @param idPago the idPago to set
	 */
	public void setIdPago(String idPago) {
		this.idPago = idPago;
	}
	
}
