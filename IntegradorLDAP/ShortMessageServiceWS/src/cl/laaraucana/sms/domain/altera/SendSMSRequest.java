package cl.laaraucana.sms.domain.altera;

public class SendSMSRequest {
	private String msg;
	private String to;

	public SendSMSRequest() {
	}

	public SendSMSRequest(String msg, String to) {
		this.msg = msg;
		this.to = to;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
