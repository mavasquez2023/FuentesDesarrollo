package cl.liv.archivos.comun.xml.lector.dto;

public class AtributoDTO {
	private String key = "";
	private String value = "";
	
	public AtributoDTO(String _key, String _value){
		key = _key;
		value = _value;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
