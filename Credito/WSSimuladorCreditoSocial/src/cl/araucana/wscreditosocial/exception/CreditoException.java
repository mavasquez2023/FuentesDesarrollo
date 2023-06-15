package cl.araucana.wscreditosocial.exception;

public class CreditoException extends Exception {
	public String getAppMsg() {
		return appMsg;
	}

	public String getAppCode() {
		return appCode;
	}

	String appMsg = null;
	String appCode = null;

	public CreditoException(String msg) {
		appMsg = msg;
	}

/*	public Tupla2Exception(String msg, String codeIn) {
		appMsg = msg;
		appCode = codeIn;
	}*/
	
	public CreditoException(String msg, String codeIn) {
		super(msg);
		appCode = codeIn;
		appMsg = msg;
	}

}
