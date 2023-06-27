/*
 * Created on Mar 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package cl.araucana.ea.struts.exceptions;

/**
 * @author jlee
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InvalidProfileException extends Exception {

	/**
	 * 
	 */
	public InvalidProfileException() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidProfileException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidProfileException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public InvalidProfileException(Throwable cause) {
		super(cause);
	}
}
