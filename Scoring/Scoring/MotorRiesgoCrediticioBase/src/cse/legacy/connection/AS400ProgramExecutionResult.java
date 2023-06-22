package cse.legacy.connection;

import java.util.List;

public class AS400ProgramExecutionResult {

	private boolean success;
	String resultData;
	List errorMessages;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getResultData() {
		return resultData;
	}
	public void setResultData(String resultData) {
		this.resultData = resultData;
	}
	public List getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(List errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	
	
	
}
