package cse.model.businessobject;

public class EvaluacionRequest {

	private Rut rutSolicitante;
	private Monto montoSolicitado;
	
	public EvaluacionRequest() {
		super();
	}

	public Rut getRutSolicitante() {
		return rutSolicitante;
	}

	public void setRutSolicitante(Rut rutSolicitante) {
		this.rutSolicitante = rutSolicitante;
	}

	public Monto getMontoSolicitado() {
		return montoSolicitado;
	}

	public void setMontoSolicitado(Monto montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}
}
