package cl.laaraucana.sms.domain.altera;

public class BatchReportRequest {
	private String batchCode; // batch_code

	public BatchReportRequest() {
	}

	public BatchReportRequest(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

}
