package cl.laaraucana.sms.domain.altera;

import java.util.ArrayList;

public class BulkSMSBatchInfo {
	private int sentCount; // sent_count
	private int invalidCount; // invalid_count
	private ArrayList<BulkSMSInvalid> invalidList; // invalid_list
	private int errorCount; // error_count
	private ArrayList<BulkSMSError> errorList; // error_list

	public BulkSMSBatchInfo() {
	}

	public BulkSMSBatchInfo(int sentCount, int invalidCount, ArrayList<BulkSMSInvalid> invalidList, int errorCount,
			ArrayList<BulkSMSError> errorList) {
		super();
		this.sentCount = sentCount;
		this.invalidCount = invalidCount;
		this.invalidList = invalidList;
		this.errorCount = errorCount;
		this.errorList = errorList;
	}

	public int getSentCount() {
		return sentCount;
	}

	public void setSentCount(int sentCount) {
		this.sentCount = sentCount;
	}

	public int getInvalidCount() {
		return invalidCount;
	}

	public void setInvalidCount(int invalidCount) {
		this.invalidCount = invalidCount;
	}

	public ArrayList<BulkSMSInvalid> getInvalidList() {
		return invalidList;
	}

	public void setInvalidList(ArrayList<BulkSMSInvalid> invalidList) {
		this.invalidList = invalidList;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public ArrayList<BulkSMSError> getErrorList() {
		return errorList;
	}

	public void setErrorList(ArrayList<BulkSMSError> errorList) {
		this.errorList = errorList;
	}
}
