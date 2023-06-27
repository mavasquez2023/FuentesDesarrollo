package cl.laaraucana.capaservicios.xml.solicitud;

import java.util.ArrayList;

public class ListaTEF {
	private ArrayList<Transferencia> transferencia = new ArrayList<Transferencia>();

	public ListaTEF() {
		super();
	}

	public ListaTEF(ArrayList<Transferencia> transferencia) {
		super();
		this.transferencia = transferencia;
	}

	public ArrayList<Transferencia> getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(ArrayList<Transferencia> transferencia) {
		this.transferencia = transferencia;
	}

}
