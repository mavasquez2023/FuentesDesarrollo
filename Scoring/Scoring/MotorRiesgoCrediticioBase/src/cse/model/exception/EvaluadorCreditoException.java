package cse.model.exception;

public class EvaluadorCreditoException extends Exception {

	private static final long serialVersionUID = 1L;

	private String codigoError;
	private String descripcionError;

	public EvaluadorCreditoException() {
		super();
	}

	public EvaluadorCreditoException(String codigoError, String descripcionError) {
		super();
		this.codigoError = codigoError;
		this.descripcionError = descripcionError;
	}

	public EvaluadorCreditoException(String message) {
		super(message);
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

}
