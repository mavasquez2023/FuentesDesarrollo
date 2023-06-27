package cl.araucana.cp.hibernate.beans;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class T0031VO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int rutEmpresa;
	private int folioNomina;
	private String fechaVencimiento;
	private Set detalleCuotas;
	

	public T0031VO()
	{
		super();

	}
	
	public T0031VO( int rutEmpresa, int folioNomina, String fechaVencimiento, Set detalleCuotas)
	{
		super();
		this.rutEmpresa=rutEmpresa;
		this.folioNomina=folioNomina;
		this.fechaVencimiento= fechaVencimiento;
		this.detalleCuotas= detalleCuotas;
	}
	
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * @return el detalleCuotas
	 */
	public Set getDetalleCuotas() {
		return detalleCuotas;
	}

	/**
	 * @param detalleCuotas el detalleCuotas a establecer
	 */
	public void setDetalleCuotas(Set detalleCuotas) {
		this.detalleCuotas = detalleCuotas;
	}

	/**
	 * @return el fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento el fechaVencimiento a establecer
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return el folioNomina
	 */
	public int getFolioNomina() {
		return folioNomina;
	}

	/**
	 * @param folioNomina el folioNomina a establecer
	 */
	public void setFolioNomina(int folioNomina) {
		this.folioNomina = folioNomina;
	}

	/**
	 * @return el rutEmpresa
	 */
	public int getRutEmpresa() {
		return rutEmpresa;
	}

	/**
	 * @param rutEmpresa el rutEmpresa a establecer
	 */
	public void setRutEmpresa(int rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}


	
}
