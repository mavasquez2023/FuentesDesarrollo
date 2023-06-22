package cl.laaraucana.mediopagosilws.core.database.exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException(String msg, Exception e) {
		super(msg, e);
	}

}
