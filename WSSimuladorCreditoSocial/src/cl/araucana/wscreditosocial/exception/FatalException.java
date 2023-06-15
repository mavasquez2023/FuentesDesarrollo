package cl.araucana.wscreditosocial.exception;

public class FatalException extends Exception {
	public String getAppMsg() {
		return appMsg;
	}

	public String getAppCode() {
		return appCode;
	}

	String appMsg = null;
	String appCode = null;

	public FatalException(String msg) {
		appMsg = msg;
	}

	public FatalException(String msg, String codeIn) {
		appMsg = msg;
		appCode = codeIn;
	}

}
