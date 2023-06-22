package cse.model.exception;

/**
 * Exception thrown when a product with duplicate id is to be inserted.
 * 
 */
public class DuplicateIdException extends ScoringModuleException {
	/**
	 * Constructor.
	 * 
	 * @param msg the error message
	 * @param cause the root cause of the exception
	 */
	public DuplicateIdException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
