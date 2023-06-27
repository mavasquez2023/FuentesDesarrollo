/*
 * Created on Aug 3, 2005
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
public class ServiceUnavailableException extends Exception {

	/**
	 * 
	 */
	public ServiceUnavailableException() {
		super();
	}

	/**
	 * @param message
	 */
	public ServiceUnavailableException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceUnavailableException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ServiceUnavailableException(Throwable cause) {
		super(cause);
	}
}
