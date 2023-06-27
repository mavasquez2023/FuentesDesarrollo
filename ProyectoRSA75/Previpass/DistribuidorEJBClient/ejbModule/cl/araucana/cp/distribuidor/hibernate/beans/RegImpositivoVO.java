package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;
import java.util.HashMap;

public class RegImpositivoVO implements Serializable
{
	private static final long serialVersionUID = -1637760793172856955L;
	private int idEntExCaja;
	private int idRegImpositivo;
	private float tasaPension;
	private String descripcion;

	public RegImpositivoVO()
	{
		super();
	}

	public RegImpositivoVO(int idEntExCaja, int idRegImpositivo) 
	{
		super();
		this.idEntExCaja = idEntExCaja;
		this.idRegImpositivo = idRegImpositivo;
	}

	public RegImpositivoVO(int idEntExCaja, int idRegImpositivo, float tasaPension, String descripcion)
	{
		super();
		this.idEntExCaja = idEntExCaja;
		this.idRegImpositivo = idRegImpositivo;
		this.tasaPension = tasaPension;
		this.descripcion = descripcion;
	}
	public String getDescripcion()
	{
		return this.descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public int getIdEntExCaja()
	{
		return this.idEntExCaja;
	}
	public void setIdEntExCaja(int idEntExCaja)
	{
		this.idEntExCaja = idEntExCaja;
	}
	public int getIdRegImpositivo()
	{
		return this.idRegImpositivo;
	}
	public void setIdRegImpositivo(int idRegImpositivo)
	{
		this.idRegImpositivo = idRegImpositivo;
	}
	public float getTasaPension()
	{
		return this.tasaPension;
	}
	public void setTasaPension(float tasaPension)
	{
		this.tasaPension = tasaPension;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idEntExCaja));
		parametros.put("2", String.valueOf(this.idRegImpositivo));
		parametros.put("3", String.valueOf(this.descripcion));
		parametros.put("4", String.valueOf(this.tasaPension));
		return parametros;
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((this.descripcion == null) ? 0 : this.descripcion.hashCode());
		result = PRIME * result + this.idEntExCaja;
		result = PRIME * result + this.idRegImpositivo;
		result = PRIME * result + Float.floatToIntBits(this.tasaPension);
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final RegImpositivoVO other = (RegImpositivoVO) obj;
		if (this.descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!this.descripcion.equals(other.descripcion))
			return false;
		if (this.idEntExCaja != other.idEntExCaja)
			return false;
		if (this.idRegImpositivo != other.idRegImpositivo)
			return false;
		if (Float.floatToIntBits(this.tasaPension) != Float.floatToIntBits(other.tasaPension))
			return false;
		return true;
	}
}
