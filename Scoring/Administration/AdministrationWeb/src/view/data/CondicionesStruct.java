package view.data;

import java.util.List;

public class CondicionesStruct {

	private CondicionMasterKey key;
	private String strTipoRiesgo;
	private String strTipoRenta;
	private String strPerfilRiesgo;
	// Array de tipo String (a ver si funciona)
	private List condiciones;	
	
	public CondicionesStruct() {
		super();
	}

	public CondicionesStruct(CondicionMasterKey key, String strTipoRiesgo, String strTipoRenta,
			String strPerfilRiesgo, List condiciones, List idCondicionesArray ) {
		super();
		this.key = key;
		this.strTipoRiesgo = strTipoRiesgo;
		this.strTipoRenta = strTipoRenta;
		this.strPerfilRiesgo = strPerfilRiesgo;
		this.condiciones = condiciones;		
	}	

	public CondicionMasterKey getKey() {
		return key;
	}

	public void setKey(CondicionMasterKey key) {
		this.key = key;
	}
	
	public String getStrTipoRenta() {
		return strTipoRenta;
	}

	public void setStrTipoRenta(String strTipoRenta) {
		this.strTipoRenta = strTipoRenta;
	}

	public String getStrPerfilRiesgo() {
		return strPerfilRiesgo;
	}

	public void setStrPerfilRiesgo(String strPerfilRiesgo) {
		this.strPerfilRiesgo = strPerfilRiesgo;
	}

	public List getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List condiciones) {
		this.condiciones = condiciones;
	}

	public void setStrTipoRiesgo(String strTipoRiesgo) {
		this.strTipoRiesgo = strTipoRiesgo;
	}
	
	public String getStrTipoRiesgo() {
		return strTipoRiesgo;
	}
	

}
