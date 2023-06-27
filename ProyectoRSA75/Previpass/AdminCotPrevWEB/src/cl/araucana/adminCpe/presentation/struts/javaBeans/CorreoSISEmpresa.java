package cl.araucana.adminCpe.presentation.struts.javaBeans;

import java.io.Serializable;

public class CorreoSISEmpresa implements Serializable 
{
	private static final long serialVersionUID = 458208747980746335L;
	
	private String periodo;
	private String rutEmpresa;
	private String dvEmpresa;
	private String razonSocial;
	private String rutTrabajador;
	private String dvTrabajador;
	private String nombreTrabajador;
	private String codAFP;
	private String montoCancelado;
	
	public String getCodAFP() {
		return codAFP;
	}
	public void setCodAFP(String codAFP) {
		this.codAFP = codAFP;
	}
	public String getDvEmpresa() {
		return dvEmpresa;
	}
	public void setDvEmpresa(String dvEmpresa) {
		this.dvEmpresa = dvEmpresa;
	}
	public String getDvTrabajador() {
		return dvTrabajador;
	}
	public void setDvTrabajador(String dvTrabajador) {
		this.dvTrabajador = dvTrabajador;
	}
	public String getMontoCancelado() {
		return montoCancelado;
	}
	public void setMontoCancelado(String montoCancelado) {
		this.montoCancelado = montoCancelado;
	}
	public String getNombreTrabajador() {
		return nombreTrabajador;
	}
	public void setNombreTrabajador(String nombreTrabajador) {
		this.nombreTrabajador = nombreTrabajador;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRutEmpresa() {
		return rutEmpresa;
	}
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	public String getRutTrabajador() {
		return rutTrabajador;
	}
	public void setRutTrabajador(String rutTrabajador) {
		this.rutTrabajador = rutTrabajador;
	}
	
	
}
