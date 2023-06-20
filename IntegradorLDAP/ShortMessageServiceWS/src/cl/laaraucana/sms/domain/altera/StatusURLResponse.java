package cl.laaraucana.sms.domain.altera;

public class StatusURLResponse {
	private String success;
	private StatusURLData data;

	public StatusURLResponse() {
	}

	public StatusURLResponse(String success, StatusURLData data) {
		this.success = success;
		this.data = data;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public StatusURLData getData() {
		return data;
	}

	public void setData(StatusURLData data) {
		this.data = data;
	}
}

