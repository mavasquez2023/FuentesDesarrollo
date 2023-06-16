package cl.araucana.ctasfam.batch.common.exceptions;

public class TechnicalException extends Exception{
	
	private String code;
	private String description;
	
	public TechnicalException(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}
	
	public TechnicalException(String code, String description, Exception e) {
		super(e);
		this.code = code;
		this.description = description;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
