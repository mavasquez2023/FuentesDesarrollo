package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class LectorEntidadMutualVO implements Serializable {

	private static final long serialVersionUID = 2743922932412574348L;
	private int idLectorEntidad;
	private int idEntidadMutual;

	private EntidadMutualVO entidadMutual;
	
	public LectorEntidadMutualVO() {
		super();
	}

	public LectorEntidadMutualVO(int idLectorEntidad, int idEntidadMutual) {
		super();
		this.idLectorEntidad = idLectorEntidad;
		this.idEntidadMutual = idEntidadMutual;
	}

	public int getidEntidadMutual() {
		return this.idEntidadMutual;
	}
	public void setidEntidadMutual(int idEntidadMutual) {
		this.idEntidadMutual = idEntidadMutual;
	}
	public int getidLectorEntidad() {
		return this.idLectorEntidad;
	}
	public void setidLectorEntidad(int idLectorEntidad) {
		this.idLectorEntidad = idLectorEntidad;
	}
	public EntidadMutualVO getEntidadMutual() {
		return this.entidadMutual;
	}
	public void setEntidadMutual(EntidadMutualVO entidadMutual) {
		this.entidadMutual = entidadMutual;
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idEntidadMutual;
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
		final LectorEntidadMutualVO other = (LectorEntidadMutualVO) obj;
		if (this.idEntidadMutual != other.idEntidadMutual)
			return false;
		if (this.idLectorEntidad != other.idLectorEntidad)
			return false;
		return true;
	}

}