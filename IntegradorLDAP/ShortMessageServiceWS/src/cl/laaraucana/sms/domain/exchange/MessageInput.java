package cl.laaraucana.sms.domain.exchange;

public class MessageInput {
	private String username;
	private String password;
	private String rut;
	private String dv;
	private String phone;
	private String message;
	private String urlCondition;
	private String urlText;

	public MessageInput() {
	}

	public MessageInput(String username, String password, String rut, String dv, String phone, String message,
			String urlCondition, String urlText) {
		this.username = username;
		this.password = password;
		this.rut = rut;
		this.dv = dv;
		this.phone = phone;
		this.message = message;
		this.urlCondition = urlCondition;
		this.urlText = urlText;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrlCondition() {
		return urlCondition;
	}

	public void setUrlCondition(String urlCondition) {
		this.urlCondition = urlCondition;
	}

	public String getUrlText() {
		return urlText;
	}

	public void setUrlText(String urlText) {
		this.urlText = urlText;
	}

}
