package cse.model.exception;

/**
 * Base checked exception for the JCatalog Project.
 * 
 */
public class ScoringModuleException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor with error message.
	 * 
	 * @param msg
	 *            the error message associated with the exception
	 */
	public ScoringModuleException(String msg) {
		super(msg);
	}

	public ScoringModuleException() {
		super();
	}

	public ScoringModuleException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor with error message and root cause.
	 * 
	 * @param msg
	 *            the error message associated with the exception
	 * @param cause
	 *            the root cause of the exception
	 */
	public ScoringModuleException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
