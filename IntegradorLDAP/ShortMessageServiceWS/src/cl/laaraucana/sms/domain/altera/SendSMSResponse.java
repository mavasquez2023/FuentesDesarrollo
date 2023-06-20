package cl.laaraucana.sms.domain.altera;

public class SendSMSResponse {
	private String success;
	private String code;

	public SendSMSResponse() {
	}

	public SendSMSResponse(String success, String code) {
		this.success = success;
		this.code = code;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
