package cl.laaraucana.rendicionpagonomina.vo;

public class ColumnaFormatoVO {
//<parametro key="PERIODO" posicion="0" validacion="not_null" />
//<parametro key="FECHA_DE_COLOCACION" posicion="29" validacion="not_null"  valor_default_null="19000101"/>
//<parametro key="NOMBRE_PENSIONADO" 		posicion="29" 	largo="40" />		
	
	private String key = null;
	private int posicion = 0;
	private int largo = 0;
	private String valorDefault = null;
	public static ColumnaFormatoVO createColumnForPlainFile(String key, int from, int to) {
		ColumnaFormatoVO columna = new ColumnaFormatoVO();
		columna.setKey(key);
		columna.setPosicion(from);
		columna.setLargo(to-from);
		return columna;
	}
	
	public static ColumnaFormatoVO createColumnCSVFile(String key, int position) {
		ColumnaFormatoVO columna = new ColumnaFormatoVO();
		columna.setKey(key);
		columna.setPosicion(position);
		return columna;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public int getLargo() {
		return largo;
	}
	public void setLargo(int largo) {
		this.largo = largo;
	}
	public String getValorDefault() {
		return valorDefault;
	}
	public void setValorDefault(String valorDefault) {
		this.valorDefault = valorDefault;
	}
	@Override
	public String toString() {
		return "ColumnaVO [key=" + key + ", posicion=" + posicion + ", largo=" + largo + ", valorDefault="
				+ valorDefault + "]";
	}
	
	
}
