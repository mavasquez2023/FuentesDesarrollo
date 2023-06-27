package cl.araucana.cp.hibernate.beans;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AF02F1EEPPVO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int rutAfiliado;
	private String dvRutAfiliado;
	private String nombreAfiliado;
	private String apellidoAfiliado;
	private String apellidoMaterno;
	private String sexoAfiliado;
		
	

	public AF02F1EEPPVO(int rutAfiliado, String dvRutAfiliado, String nombreAfiliado, 
			String apellidoAfiliado, String apellidoMaterno, String sexoAfiliado)
	{
		super();
		this.rutAfiliado= rutAfiliado;
		this.dvRutAfiliado= dvRutAfiliado;
		this.nombreAfiliado= nombreAfiliado;
		this.apellidoAfiliado= apellidoAfiliado;
		this.apellidoMaterno= apellidoMaterno;
		this.sexoAfiliado= sexoAfiliado;
	}
	
	public AF02F1EEPPVO()
	{
		super();
	}
	
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * @return el apellidoAfiliado
	 */
	public String getApellidoAfiliado() {
		return apellidoAfiliado;
	}

	/**
	 * @param apellidoAfiliado el apellidoAfiliado a establecer
	 */
	public void setApellidoAfiliado(String apellidoAfiliado) {
		this.apellidoAfiliado = apellidoAfiliado;
	}

	/**
	 * @return el apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno el apellidoMaterno a establecer
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return el dvRutAfiliado
	 */
	public String getDvRutAfiliado() {
		return dvRutAfiliado;
	}

	/**
	 * @param dvRutAfiliado el dvRutAfiliado a establecer
	 */
	public void setDvRutAfiliado(String dvRutAfiliado) {
		this.dvRutAfiliado = dvRutAfiliado;
	}

	/**
	 * @return el nombreAfiliado
	 */
	public String getNombreAfiliado() {
		return nombreAfiliado;
	}

	/**
	 * @param nombreAfiliado el nombreAfiliado a establecer
	 */
	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}

	/**
	 * @return el rutAfiliado
	 */
	public int getRutAfiliado() {
		return rutAfiliado;
	}

	/**
	 * @param rutAfiliado el rutAfiliado a establecer
	 */
	public void setRutAfiliado(int rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}

	/**
	 * @return el sexoAfiliado
	 */
	public String getSexoAfiliado() {
		return sexoAfiliado;
	}

	/**
	 * @param sexoAfiliado el sexoAfiliado a establecer
	 */
	public void setSexoAfiliado(String sexoAfiliado) {
		this.sexoAfiliado = sexoAfiliado;
	}


}
