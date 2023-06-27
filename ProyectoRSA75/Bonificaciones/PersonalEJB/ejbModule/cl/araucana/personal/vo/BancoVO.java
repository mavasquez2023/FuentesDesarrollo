package cl.araucana.personal.vo;

import java.io.Serializable;

/**
 * @author admin
 *
 */
public class BancoVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String descripcionLarca;
	private String descripcionCorta;
	private String abreviacion;
	
	public String getAbreviacion() {
		return abreviacion;
	}
	public void setAbreviacion(String abreviacion) {
		this.abreviacion = abreviacion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	public String getDescripcionLarca() {
		return descripcionLarca;
	}
	public void setDescripcionLarca(String descripcionLarca) {
		this.descripcionLarca = descripcionLarca;
	}
	
}
