package cl.araucana.spl.exceptions;

public class PagoEnLineaException extends Exception {
	private static final long serialVersionUID = -7156915685230104258L;

	public PagoEnLineaException() {
		super();
	}

	public PagoEnLineaException(String message, Throwable cause) {
		super(message, cause);
	}

	public PagoEnLineaException(String message) {
		super(message);
	}

	public PagoEnLineaException(Throwable cause) {
		super(cause);
	}
}
