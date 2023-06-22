package cse.database.dao.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = 4627992868731398084L;

	public DAOException() {
		super();
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

}
