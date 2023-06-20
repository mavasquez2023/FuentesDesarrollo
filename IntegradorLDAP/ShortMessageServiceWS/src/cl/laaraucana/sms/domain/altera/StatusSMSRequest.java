package cl.laaraucana.sms.domain.altera;

public class StatusSMSRequest {
	private String code;

	public StatusSMSRequest() {
	}

	public StatusSMSRequest(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
