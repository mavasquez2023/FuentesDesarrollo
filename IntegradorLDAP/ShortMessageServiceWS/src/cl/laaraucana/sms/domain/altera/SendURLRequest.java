package cl.laaraucana.sms.domain.altera;

public class SendURLRequest {
	private String msg;
	private String to;
	private boolean url;
	private String shortUrl;

	public SendURLRequest() {
	}

	public SendURLRequest(String msg, String to, boolean url, String shortUrl) {
		this.msg = msg;
		this.to = to;
		this.url = url;
		this.shortUrl = shortUrl;
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

	public boolean isUrl() {
		return url;
	}

	public void setUrl(boolean url) {
		this.url = url;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

}
