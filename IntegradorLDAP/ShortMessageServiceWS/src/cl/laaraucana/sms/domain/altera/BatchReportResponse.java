package cl.laaraucana.sms.domain.altera;

import java.util.ArrayList;

public class BatchReportResponse {

	private String success;
	private String status;
	private int batchSize; // batch_size
	private int countSent; // count_sent
	private ArrayList<BatchReportSentData> sentData; // sent_data

	public BatchReportResponse() {
	}

	public BatchReportResponse(String success, String status, int batchSize, int countSent,
			ArrayList<BatchReportSentData> sentData) {
		this.success = success;
		this.status = status;
		this.batchSize = batchSize;
		this.countSent = countSent;
		this.sentData = sentData;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public int getCountSent() {
		return countSent;
	}

	public void setCountSent(int countSent) {
		this.countSent = countSent;
	}

	public ArrayList<BatchReportSentData> getSentData() {
		return sentData;
	}

	public void setSentData(ArrayList<BatchReportSentData> sentData) {
		this.sentData = sentData;
	}
}
