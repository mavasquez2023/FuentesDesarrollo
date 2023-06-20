package cl.laaraucana.sms.domain.altera;

public class SendURLResponse {
	private String success;
	private String code;
	private String urlKey;

	public SendURLResponse() {
	}

	public SendURLResponse(String success, String code, String urlKey) {
		this.success = success;
		this.code = code;
		this.urlKey = urlKey;
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

	public String getUrlKey() {
		return urlKey;
	}

	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}

}
