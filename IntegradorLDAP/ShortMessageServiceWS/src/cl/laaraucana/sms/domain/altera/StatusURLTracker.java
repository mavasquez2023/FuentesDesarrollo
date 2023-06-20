package cl.laaraucana.sms.domain.altera;

public class StatusURLTracker {
	private String browser;
	private String os;
	private String createdAt; // created_at
	// IP address not included (seen) in production nor testing environment
	private String ip;

	public StatusURLTracker() {
	}

	public StatusURLTracker(String browser, String os, String createdAt, String ip) {
		this.browser = browser;
		this.os = os;
		this.createdAt = createdAt;
		this.ip = ip;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}