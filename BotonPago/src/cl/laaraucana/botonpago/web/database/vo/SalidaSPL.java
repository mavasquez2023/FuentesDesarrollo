package cl.laaraucana.botonpago.web.database.vo;

import java.util.ArrayList;

import cl.laaraucana.botonpago.web.database.ibatis.domain.Convenio;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Detpago;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Mediopago;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Pago;

public class SalidaSPL {

	private Convenio convenio;
	private ArrayList<Detpago> detPago;
	private Mediopago medioPago;
	private Pago pago;

	public Convenio getConvenio() {
		return convenio;
	}

	public ArrayList<Detpago> getDetPago() {
		return detPago;
	}

	public Mediopago getMedioPago() {
		return medioPago;
	}

	public Pago getPago() {
		return pago;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public void setDetPago(ArrayList<Detpago> detPago) {
		this.detPago = detPago;
	}

	public void setMedioPago(Mediopago medioPago) {
		this.medioPago = medioPago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

}
