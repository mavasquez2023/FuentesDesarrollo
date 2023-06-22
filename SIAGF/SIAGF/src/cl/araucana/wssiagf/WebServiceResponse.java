

package cl.araucana.wssiagf;


import java.io.PrintStream;
import java.io.Serializable;


public class WebServiceResponse implements Serializable {

	private int seqNo;
	private int docNo;
	private int code;
	private String message;

	public WebServiceResponse() {
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setDocNo(int docNo) {
		this.docNo = docNo;
	}

	public int getDocNo() {
		return docNo;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String toString() {
		return
				  "seqNo=" + seqNo + " docNo=" + docNo + " "
				+ "code=" + code + " message=" + message;
	}

	public void dump() {
		dump(System.out);
	}

	public void dump(PrintStream out) {
		out.println("code    = " + getCode());
		out.println("message = " + getMessage());
	}
}
