package cl.araucana.ctasfam.integration.jdbc.exception;

public class RentaPropuestasException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public RentaPropuestasException() {
		super("Error en el RentaPropuestaDao.");
	}
	
	public RentaPropuestasException(String message) {
		super(message);
	}

}
