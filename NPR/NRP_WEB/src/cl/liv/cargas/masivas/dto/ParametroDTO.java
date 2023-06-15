package cl.liv.cargas.masivas.dto;

public class ParametroDTO {
	private String key = "";
	private boolean unique = false;
	private int posicion = 0;
	private int largo = 0;
	public int getLargo() {
		return largo;
	}
	public void setLargo(int largo) {
		this.largo = largo;
	}
	private String validacion = "";
	private String valorDefaultNull = "";
	private String onLoad = "";
	
	public ParametroDTO(){
		
	}
	public ParametroDTO(String _key, boolean _unique, int _posicion, String _validacion){
		key = _key;
		unique = _unique;
		posicion = _posicion;
		validacion = _validacion;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public String getValidacion() {
		return validacion;
	}

	public void setValidacion(String validacion) {
		this.validacion = validacion;
	}
	public String getValorDefaultNull() {
		return valorDefaultNull;
	}
	public void setValorDefaultNull(String valorDefaultNull) {
		this.valorDefaultNull = valorDefaultNull;
	}
	public String getOnLoad() {
		return onLoad;
	}
	public void setOnLoad(String onLoad) {
		this.onLoad = onLoad;
	}
	
	
	
}
