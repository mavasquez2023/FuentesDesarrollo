package cl.laaraucana.sms.domain.altera;

public class StatusURLError {
	private String success;
	private String error;
	private String errorCode;

	public StatusURLError() {
	}

	public StatusURLError(String success, String error, String errorCode) {
		this.success = success;
		this.error = error;
		this.errorCode = errorCode;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
