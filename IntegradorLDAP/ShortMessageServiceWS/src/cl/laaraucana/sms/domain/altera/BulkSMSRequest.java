package cl.laaraucana.sms.domain.altera;

import java.util.ArrayList;

public class BulkSMSRequest {
	private ArrayList<SendSMSRequest> messages;

	public BulkSMSRequest() {
	}

	public BulkSMSRequest(ArrayList<SendSMSRequest> messages) {
		super();
		this.messages = messages;
	}

	public ArrayList<SendSMSRequest> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<SendSMSRequest> messages) {
		this.messages = messages;
	}

}
