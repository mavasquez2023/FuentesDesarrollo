package view.data;

public class CondicionMasterKey {

	private int tipoRiesgo;
	private int tipoRenta;
	private int perfilRiesgo;

	public CondicionMasterKey() {
		super();
	}

	public CondicionMasterKey(int tipoRiesgo, int tipoRenta, int perfilRiesgo) {
		super();
		this.tipoRiesgo = tipoRiesgo;
		this.tipoRenta = tipoRenta;
		this.perfilRiesgo = perfilRiesgo;
	}

	public int getTipoRiesgo() {
		return tipoRiesgo;
	}

	public void setTipoRiesgo(int tipoRiesgo) {
		this.tipoRiesgo = tipoRiesgo;
	}

	public int getTipoRenta() {
		return tipoRenta;
	}

	public int getPerfilRiesgo() {
		return perfilRiesgo;
	}

	public void setPerfilRiesgo(int perfilRiesgo) {
		this.perfilRiesgo = perfilRiesgo;
	}

	public void setTipoRenta(int tipoRenta) {
		this.tipoRenta = tipoRenta;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + perfilRiesgo;
		result = prime * result + tipoRenta;
		result = prime * result + tipoRiesgo;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CondicionMasterKey other = (CondicionMasterKey) obj;
		if (perfilRiesgo != other.perfilRiesgo)
			return false;
		if (tipoRenta != other.tipoRenta)
			return false;
		if (tipoRiesgo != other.tipoRiesgo)
			return false;
		return true;
	}

	public String toString() {
		return super.toString() + "[ TRG: " + getTipoRiesgo() + ", TRT: " + getTipoRenta() + ", PR: " +getPerfilRiesgo() +" ]" ; 
	}

	

}
