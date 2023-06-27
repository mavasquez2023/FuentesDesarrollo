package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class LectorEntidadPensionVO implements Serializable {

	private static final long serialVersionUID = 2743922932412574348L;
	private int idLectorEntidad;
	private int idEntidadPension;

	private EntidadPensionVO entidadPension;

	public LectorEntidadPensionVO() {
		super();
	}

	public LectorEntidadPensionVO(int idLectorEntidad, int idEntidadPension) {
		super();
		this.idLectorEntidad = idLectorEntidad;
		this.idEntidadPension = idEntidadPension;
	}

	public int getidEntidadPension() {
		return this.idEntidadPension;
	}
	public void setidEntidadPension(int idEntidadPension) {
		this.idEntidadPension = idEntidadPension;
	}
	public int getidLectorEntidad() {
		return this.idLectorEntidad;
	}
	public void setidLectorEntidad(int idLectorEntidad) {
		this.idLectorEntidad = idLectorEntidad;
	}
	public EntidadPensionVO getEntidadPension() {
		return this.entidadPension;
	}
	public void setEntidadPension(EntidadPensionVO entidadPension) {
		this.entidadPension = entidadPension;
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idEntidadPension;
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
		final LectorEntidadPensionVO other = (LectorEntidadPensionVO) obj;
		if (this.idEntidadPension != other.idEntidadPension)
			return false;
		if (this.idLectorEntidad != other.idLectorEntidad)
			return false;
		return true;
	}

}