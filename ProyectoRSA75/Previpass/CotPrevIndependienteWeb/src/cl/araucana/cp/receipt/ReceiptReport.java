package cl.araucana.cp.receipt;

import java.util.ArrayList;
import java.util.List;

import cl.araucana.core.util.AbsoluteDateTime;

public class ReceiptReport
{
	private int receiptNo;
	private String sender;
	private AbsoluteDateTime received;
	private long serviceTime;
	private int status;
	private String statusMessage = "OK";
	private List acceptedFiles = new ArrayList();
	private List rejectedFiles = new ArrayList();

	public ReceiptReport()
	{
	}

	public void setReceiptNo(int receiptNo)
	{
		this.receiptNo = receiptNo;
	}

	public int getReceiptNo()
	{
		return this.receiptNo;
	}

	public void setSender(String sender)
	{
		this.sender = sender;
	}

	public String getSender()
	{
		return this.sender;
	}

	public void setReceived(AbsoluteDateTime received)
	{
		this.received = received;
	}

	public AbsoluteDateTime getReceived()
	{
		return this.received;
	}

	public void setServiceTime(long serviceTime)
	{
		this.serviceTime = serviceTime;
	}

	public long getServiceTime()
	{
		return this.serviceTime;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public int getStatus()
	{
		return this.status;
	}

	public void setStatus(int status, String statusMessage)
	{
		setStatus(status);
		setStatusMessage(statusMessage);
	}

	public void setStatusMessage(String statusMessage)
	{
		this.statusMessage = statusMessage;
	}

	public String getStatusMessage()
	{
		return this.statusMessage;
	}

	public void addAcceptedFile(String id, String fileName, int size, int compressedSize, int nRecords)
	{
		this.acceptedFiles.add(new AcceptedFile(id, fileName, size, compressedSize, nRecords));
	}

	public void addAcceptedFile(String id, String fileName, int size, int nRecords)
	{
		this.acceptedFiles.add(new AcceptedFile(id, fileName, size, nRecords));
	}

	public List getAcceptedFiles()
	{
		return this.acceptedFiles;
	}

	public int getNAcceptedFiles()
	{
		return this.acceptedFiles.size();
	}

	public void addRejectedFile(String id, String fileName, String reason)
	{
		this.rejectedFiles.add(new RejectedFile(id, fileName, reason));
	}

	public List getRejectedFiles()
	{
		return this.rejectedFiles;
	}

	public int getNRejectedFiles()
	{
		return this.rejectedFiles.size();
	}

	public int getNFilesReceived()
	{
		return this.acceptedFiles.size() + this.rejectedFiles.size();
	}
}
