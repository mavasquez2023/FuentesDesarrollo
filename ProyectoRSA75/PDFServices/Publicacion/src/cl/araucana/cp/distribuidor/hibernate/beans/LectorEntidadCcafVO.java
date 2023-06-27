package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class LectorEntidadCcafVO implements Serializable {

	private static final long serialVersionUID = 2743922932412574348L;
	private int idLectorEntidad;
	private int idEntidadCcaf;

	private EntidadCCAFVO entidadCcaf;

	public LectorEntidadCcafVO() {
		super();
	}

	public LectorEntidadCcafVO(int idLectorEntidad, int idEntidadCcaf) {
		super();
		this.idLectorEntidad = idLectorEntidad;
		this.idEntidadCcaf = idEntidadCcaf;
	}

	public int getidEntidadCcaf() {
		return this.idEntidadCcaf;
	}
	public void setidEntidadCcaf(int idEntidadCcaf) {
		this.idEntidadCcaf = idEntidadCcaf;
	}
	public int getidLectorEntidad() {
		return this.idLectorEntidad;
	}
	public void setidLectorEntidad(int idLectorEntidad) {
		this.idLectorEntidad = idLectorEntidad;
	}
	public EntidadCCAFVO getEntidadCcaf() {
		return this.entidadCcaf;
	}

	public void setEntidadCcaf(EntidadCCAFVO entidadCcaf) {
		this.entidadCcaf = entidadCcaf;
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idEntidadCcaf;
		result = PRIME * result + this.idLectorEntidad;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final LectorEntidadCcafVO other = (LectorEntidadCcafVO) obj;
		if (this.idEntidadCcaf != other.idEntidadCcaf)
			return false;
		if (this.idLectorEntidad != other.idLectorEntidad)
			return false;
		return true;
	}
}