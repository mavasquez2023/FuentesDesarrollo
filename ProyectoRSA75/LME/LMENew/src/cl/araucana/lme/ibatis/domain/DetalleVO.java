/**
 * 
 */
package cl.araucana.lme.ibatis.domain;

/**
 * @author usist199
 *
 */
public class DetalleVO {
	private String fechaInicioLicencia="";
	private int rutAfiliado;
	private String dvRutAfiliado;
	private int tipoLicencia;
	private String nomAfiliado;
	private int rutEmpresa;
	private String dvRutEmpresa;
	public String getDvRutAfiliado() {
		return dvRutAfiliado;
	}
	public void setDvRutAfiliado(String dvRutAfiliado) {
		this.dvRutAfiliado = dvRutAfiliado;
	}
	public String getDvRutEmpresa() {
		return dvRutEmpresa;
	}
	public void setDvRutEmpresa(String dvRutEmpresa) {
		this.dvRutEmpresa = dvRutEmpresa;
	}
	public String getNomAfiliado() {
		return nomAfiliado;
	}
	public void setNomAfiliado(String nomAfiliado) {
		this.nomAfiliado = nomAfiliado;
	}
	public String getFechaInicioLicencia() {
		return fechaInicioLicencia;
	}
	public String getPeriodoInicioLicencia() {
		if(fechaInicioLicencia.length()>6){
			return fechaInicioLicencia.substring(0, 6);
		}
		return "190001";
	}
	public void setFechaInicioLicencia(String fechaInicioLicencia) {
		this.fechaInicioLicencia = fechaInicioLicencia;
	}
	public int getRutAfiliado() {
		return rutAfiliado;
	}
	public void setRutAfiliado(int rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}
	public int getRutEmpresa() {
		return rutEmpresa;
	}
	public void setRutEmpresa(int rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	public int getTipoLicencia() {
		return tipoLicencia;
	}
	public void setTipoLicencia(int tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}
}
