package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class LectorEntidadSaludVO implements Serializable {

	private static final long serialVersionUID = 2743922932412574348L;
	private int idLectorEntidad;
	private int idEntidadSalud;

	private EntidadSaludVO entidadSalud;

	public LectorEntidadSaludVO() {
		super();
	}

	public LectorEntidadSaludVO(int idLectorEntidad, int idEntidadSalud) {
		super();
		this.idLectorEntidad = idLectorEntidad;
		this.idEntidadSalud = idEntidadSalud;
	}

	public int getidEntidadSalud() {
		return this.idEntidadSalud;
	}
	public void setidEntidadSalud(int idEntidadSalud) {
		this.idEntidadSalud = idEntidadSalud;
	}
	public int getidLectorEntidad() {
		return this.idLectorEntidad;
	}
	public void setidLectorEntidad(int idLectorEntidad) {
		this.idLectorEntidad = idLectorEntidad;
	}
	public EntidadSaludVO getEntidadSalud() {
		return this.entidadSalud;
	}
	public void setEntidadSalud(EntidadSaludVO entidadSalud) {
		this.entidadSalud = entidadSalud;
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idEntidadSalud;
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
		final LectorEntidadSaludVO other = (LectorEntidadSaludVO) obj;
		if (this.idEntidadSalud != other.idEntidadSalud)
			return false;
		if (this.idLectorEntidad != other.idLectorEntidad)
			return false;
		return true;
	}

}