package cse.model.businessobject;

public class Rut {

	public Rut() {
		super();	
	}

	public Rut(String numero, String digitoChequeo) {
		super();
		this.numero = numero;
		this.digitoChequeo = digitoChequeo;
	}

	private String numero;
	private String digitoChequeo;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDigitoChequeo() {
		return digitoChequeo;
	}

	public void setDigitoChequeo(String digitoChequeo) {
		this.digitoChequeo = digitoChequeo;
	}

	public String toString() {
		return super.toString() + "[ " + this.getNumero() + this.getDigitoChequeo() + " ]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((digitoChequeo == null) ? 0 : digitoChequeo.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rut other = (Rut) obj;
		if (digitoChequeo == null) {
			if (other.digitoChequeo != null)
				return false;
		} else if (!digitoChequeo.equals(other.digitoChequeo))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	
}
