package cl.araucana.tesoreria.util;

import cl.araucana.tesoreria.util.BusinessException;


public class FlowException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public FlowException() {
		super();
	}

	public FlowException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FlowException(String arg0) {
		super(arg0);
	}

	public FlowException(Throwable arg0) {
		super(arg0);
	}
	
	
	

}
