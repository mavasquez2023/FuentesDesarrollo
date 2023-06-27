/*
 * Created on Apr 20, 2005
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
public class DuplicateFormSubmissionException extends Exception {
	/**
	 * 
	 */
	public DuplicateFormSubmissionException() {
		super();
	}

	/**
	 * @param message
	 */
	public DuplicateFormSubmissionException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DuplicateFormSubmissionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public DuplicateFormSubmissionException(Throwable cause) {
		super(cause);
	}
}
