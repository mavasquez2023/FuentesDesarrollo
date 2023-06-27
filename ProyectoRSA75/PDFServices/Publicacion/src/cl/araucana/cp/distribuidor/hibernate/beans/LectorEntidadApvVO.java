package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class LectorEntidadApvVO implements Serializable {

	private static final long serialVersionUID = 2743922932412574348L;
	private int idLectorEntidad;
	private int idEntidadApv;

	private EntidadApvVO entidadApv;

	public LectorEntidadApvVO() {
		super();
	}

	public LectorEntidadApvVO(int idLectorEntidad, int idEntidadApv) {
		super();
		this.idLectorEntidad = idLectorEntidad;
		this.idEntidadApv = idEntidadApv;
	}

	public int getidEntidadApv() {
		return this.idEntidadApv;
	}
	public void setidEntidadApv(int idEntidadApv) {
		this.idEntidadApv = idEntidadApv;
	}
	public int getidLectorEntidad() {
		return this.idLectorEntidad;
	}
	public void setidLectorEntidad(int idLectorEntidad) {
		this.idLectorEntidad = idLectorEntidad;
	}
	public EntidadApvVO getEntidadApv() {
		return this.entidadApv;
	}
	public void setEntidadApv(EntidadApvVO entidadApv) {
		this.entidadApv = entidadApv;
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idEntidadApv;
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
		final LectorEntidadApvVO other = (LectorEntidadApvVO) obj;
		if (this.idEntidadApv != other.idEntidadApv)
			return false;
		if (this.idLectorEntidad != other.idLectorEntidad)
			return false;
		return true;
	}
}