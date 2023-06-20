/*
 * Created on 03-11-2011
 *
 */
package cl.araucana.lme.liq.svc.exception;

/**
 * @author j-factory
 *
 */
public class SvcException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6924355867684615464L;

	public SvcException() {
		super();
	}

	public SvcException(String message) {
		super(message);
	}

	public SvcException(Throwable cause) {
		super(cause);
	}

	public SvcException(String message, Throwable cause) {
		super(message, cause);
	}

}
