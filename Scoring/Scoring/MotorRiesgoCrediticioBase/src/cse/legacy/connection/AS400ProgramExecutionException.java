package cse.legacy.connection;


public class AS400ProgramExecutionException extends Exception {

	private static final long serialVersionUID = 8268335244583554203L;

	public AS400ProgramExecutionException() {
		super();
	}

	public AS400ProgramExecutionException(String message) {
		super(message);
	}

//	//TODO crear un atributo y metodos para manejar lista de mensajes para esta exception
//	public AS400ProgramExecutionException(List messages) {
//		super((String)messages.get(0));
//	}
	
	public AS400ProgramExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	public AS400ProgramExecutionException(Throwable cause) {
		super(cause);
	}
	
}
