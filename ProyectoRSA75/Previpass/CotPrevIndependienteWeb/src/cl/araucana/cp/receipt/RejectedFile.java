

package cl.araucana.cp.receipt;

public class RejectedFile 
{
	private String id;
	private String fileName;
	
	private String reason;

	public RejectedFile() {
	}

	public RejectedFile(String id, String fileName, String reason) {
		setID(id);
		setFileName(fileName);
		setReason(reason);
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return this.id;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return this.reason;
	}
}
