package cl.laaraucana.sms.domain.altera;

public class BulkSMSResponse {
	private String success;
	private String batchCode; // batch_code
	private BulkSMSBatchInfo batchInfo; // batch_info

	public BulkSMSResponse() {
	}

	public BulkSMSResponse(String success, String batchCode, BulkSMSBatchInfo batchInfo) {
		this.success = success;
		this.batchCode = batchCode;
		this.batchInfo = batchInfo;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public BulkSMSBatchInfo getBatchInfo() {
		return batchInfo;
	}

	public void setBatchInfo(BulkSMSBatchInfo batchInfo) {
		this.batchInfo = batchInfo;
	}
}