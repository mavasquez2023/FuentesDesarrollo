package cl.laaraucana.sms.domain.altera;

public class StatusSMSResponse {
	private String success;
	private StatusSMSData data;

	public StatusSMSResponse() {
	}

	public StatusSMSResponse(String success, StatusSMSData data) {
		this.success = success;
		this.data = data;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public StatusSMSData getData() {
		return data;
	}

	public void setData(StatusSMSData data) {
		this.data = data;
	}
}