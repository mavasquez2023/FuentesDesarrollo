package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;

public class InformacionDeclaraNoPago implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8860769649495032527L;
	
	private long rutEmpresa;
	private long idConvenio;
	private String razonSocial;
	private long codigoBarra;
	private String periodo;
	private String rutFormateado;
	
	public long getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(long codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public long getIdConvenio() {
		return idConvenio;
	}
	public void setIdConvenio(long idConvenio) {
		this.idConvenio = idConvenio;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public long getRutEmpresa() {
		return rutEmpresa;
	}
	public void setRutEmpresa(long rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getRutFormateado() {
		return rutFormateado;
	}
	public void setRutFormateado(String rutFormateado) {
		this.rutFormateado = rutFormateado;
	}
	}
