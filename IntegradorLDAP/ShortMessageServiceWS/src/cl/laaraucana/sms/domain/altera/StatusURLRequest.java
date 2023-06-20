package cl.laaraucana.sms.domain.altera;

public class StatusURLRequest {
	private String urlKey;

	public StatusURLRequest() {
	}

	public StatusURLRequest(String urlKey) {
		this.urlKey = urlKey;
	}

	public String getUrlKey() {
		return urlKey;
	}

	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}
}