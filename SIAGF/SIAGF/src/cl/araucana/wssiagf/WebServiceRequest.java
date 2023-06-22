

package cl.araucana.wssiagf;


import java.io.Serializable;


public class WebServiceRequest implements Serializable {

	private String sysID;
	private int reqID;
	private int opID;
	private int status;
	private String parameter;
	private int code;
	private String message;
	private String timestamp;
	private long serviceTime;

	private SIAGFBusinessTO siagfTO;

	public WebServiceRequest() {
	}

	public void setSysID(String sysID) {
		this.sysID = sysID;
	}

	public String getSysID() {
		return sysID;
	}

	public void setReqID(int reqID) {
		this.reqID = reqID;
	}

	public int getReqID() {
		return reqID;
	}

	public void setOpID(int opID) {
		this.opID = opID;
	}

	public int getOpID() {
		return opID;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter.trim();
	}

	public String getParameter() {
		return parameter;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setMessage(String message) {
		this.message = message.trim();
	}

	public String getMessage() {
		return message;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp.trim();
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setServiceTime(long serviceTime) {
		this.serviceTime = serviceTime;
	}

	public long getServiceTime() {
		return serviceTime;
	}

	public void setSiagfTO(SIAGFBusinessTO siagfTO) {
		this.siagfTO = siagfTO;
	}

	public SIAGFBusinessTO getSiagfTO() {
		return siagfTO;
	}

	public String getID() {
		return getSysID() + "/" + getReqID();
	}

	public String toString() {
		return
				  "sysID=" + sysID + " reqID=" + reqID + " "
				+ "opID=" + opID + " status=" + status + " "
				+ "parameter=" + parameter + " code=" + code + " "
				+ "message=" + message + " timestamp=" + timestamp + " "
				+ "serviceTime=" + serviceTime;
	}
}
