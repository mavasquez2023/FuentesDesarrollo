package cl.laaraucana.sms.assembly;

import cl.laaraucana.sms.domain.exchange.MessageInput;
import cl.laaraucana.sms.domain.exchange.MessageOutput;
import cl.laaraucana.sms.domain.exchange.StatusSMSInput;
import cl.laaraucana.sms.domain.exchange.StatusSMSOutput;
import cl.laaraucana.sms.domain.exchange.StatusURLInput;
import cl.laaraucana.sms.domain.exchange.StatusURLOutput;

public class ExchangeIO {

	private MessageInput messageInput;
	private MessageOutput messageOutput;
	private StatusSMSInput statusSMSInput;
	private StatusSMSOutput statusSMSOutput;
	private StatusURLInput statusURLInput;
	private StatusURLOutput statusURLOutput;

	public MessageInput getMessageInput() {
		if (null == messageInput)
			setMessageInput();
		return messageInput;
	}

	public void setMessageInput() {
		if (null == messageInput) {
			messageInput = new MessageInput();
			messageInput.setUsername("ASSEMBLER");
			messageInput.setPassword("ASSEMBLY");
			messageInput.setRut("15636381");
			messageInput.setDv("7");
			messageInput.setPhone("56990842361");
			messageInput.setMessage("Hola! Somos La Araucana, visitanos en [url]");
			messageInput.setUrlCondition("si");
			messageInput.setUrlText("https://www.laaraucana.cl");
		}
	}

	public MessageOutput getMessageOutput() {
		if (null == messageOutput)
			setMessageOutput();
		return messageOutput;
	}

	private void setMessageOutput() {
		if (null == messageOutput) {
			messageOutput = new MessageOutput();
			messageOutput.setPhone("56990842361");
			messageOutput.setResult("OK");
			messageOutput.setIdSMS("ID_SMS");
			messageOutput.setIdURL("ID_URL");
			messageOutput.setStatusCode("LOG");
			messageOutput.setStatusDescription("MESSAGE");
		}
	}

	public StatusSMSInput getStatusSMSInput() {
		return statusSMSInput;
	}

	public void setStatusSMSInput(StatusSMSInput statusSMSInput) {
		this.statusSMSInput = statusSMSInput;
	}

	public StatusSMSOutput getStatusSMSOutput() {
		return statusSMSOutput;
	}

	public void setStatusSMSOutput(StatusSMSOutput statusSMSOutput) {
		this.statusSMSOutput = statusSMSOutput;
	}

	public StatusURLInput getStatusURLInput() {
		return statusURLInput;
	}

	public void setStatusURLInput(StatusURLInput statusURLInput) {
		this.statusURLInput = statusURLInput;
	}

	public StatusURLOutput getStatusURLOutput() {
		return statusURLOutput;
	}

	public void setStatusURLOutput(StatusURLOutput statusURLOutput) {
		this.statusURLOutput = statusURLOutput;
	}

}
