package cl.laaraucana.sms.domain.altera;

public class BulkSMSInvalid {
	private String to;
	private String msg;
	private String error;

	public BulkSMSInvalid() {
	}

	public BulkSMSInvalid(String to, String msg, String error) {
		this.to = to;
		this.msg = msg;
		this.error = error;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
