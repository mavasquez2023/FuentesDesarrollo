package cl.araucana.tupla2.exception;

public class Tupla2Exception extends Exception {
	public String getAppMsg() {
		return appMsg;
	}

	public String getAppCode() {
		return appCode;
	}

	String appMsg = null;
	String appCode = null;

	public Tupla2Exception(String msg) {
		appMsg = msg;
	}

/*	public Tupla2Exception(String msg, String codeIn) {
		appMsg = msg;
		appCode = codeIn;
	}*/
	
	public Tupla2Exception(String msg, String codeIn) {
		super(msg);
		appCode = codeIn;
		appMsg = msg;
	}

}
