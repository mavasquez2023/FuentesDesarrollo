package cl.laaraucana.sms.domain.altera;

public class StatusSMSData {
	private String message;
	private String tag;
	private String code;
	private String to;
	private String status;
	private String callback;

	private String sentAt;
	private String receivedAt;
	private String error;

	public StatusSMSData() {
	}

	public StatusSMSData(String message, String tag, String code, String to, String status, String callback,
			String sentAt, String receivedAt, String error) {
		this.message = message;
		this.tag = tag;
		this.code = code;
		this.to = to;
		this.status = status;
		this.callback = callback;
		this.sentAt = sentAt;
		this.receivedAt = receivedAt;
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getSentAt() {
		return sentAt;
	}

	public void setSentAt(String sentAt) {
		this.sentAt = sentAt;
	}

	public String getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(String receivedAt) {
		this.receivedAt = receivedAt;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
