package cl.laaraucana.sms.domain.altera;

public class BatchReportSentData {
	private String message;
	private String code;
	private String to;
	private String status;
	private String sentAt; // sent_at
	private String receivedAt; // received_at

	public BatchReportSentData() {
	}

	public BatchReportSentData(String message, String code, String to, String status, String sentAt,
			String receivedAt) {
		this.message = message;
		this.code = code;
		this.to = to;
		this.status = status;
		this.sentAt = sentAt;
		this.receivedAt = receivedAt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

}
