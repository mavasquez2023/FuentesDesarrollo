/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.ibatis.vo;

import java.util.Date;

/**
 * @author IBM Software Factory
 *
 */
public class BeneficioEntity {
	private String codigoBeneficio;
	private String codigoConvenio;
	private String codigoProducto;
	private String descripcionBeneficio;
	private String generaComprobanteTesoreria;
	private int concepto;
	private int areaNegocio;
	private Date fechaCreacion;
	private String usuarioCreacion;
	/**
	 * @return the codigoBeneficio
	 */
	public String getCodigoBeneficio() {
		return codigoBeneficio;
	}
	/**
	 * @param codigoBeneficio the codigoBeneficio to set
	 */
	public void setCodigoBeneficio(String codigoBeneficio) {
		this.codigoBeneficio = codigoBeneficio;
	}
	/**
	 * @return the codigoConvenio
	 */
	public String getCodigoConvenio() {
		return codigoConvenio;
	}
	/**
	 * @param codigoConvenio the codigoConvenio to set
	 */
	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}
	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	/**
	 * @return the descripcionBeneficio
	 */
	public String getDescripcionBeneficio() {
		return descripcionBeneficio;
	}
	/**
	 * @param descripcionBeneficio the descripcionBeneficio to set
	 */
	public void setDescripcionBeneficio(String descripcionBeneficio) {
		this.descripcionBeneficio = descripcionBeneficio;
	}
	/**
	 * @return the generaComprobanteTesoreria
	 */
	public String getGeneraComprobanteTesoreria() {
		return generaComprobanteTesoreria;
	}
	/**
	 * @param generaComprobanteTesoreria the generaComprobanteTesoreria to set
	 */
	public void setGeneraComprobanteTesoreria(String generaComprobanteTesoreria) {
		this.generaComprobanteTesoreria = generaComprobanteTesoreria;
	}
	/**
	 * @return the concepto
	 */
	public int getConcepto() {
		return concepto;
	}
	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(int concepto) {
		this.concepto = concepto;
	}
	/**
	 * @return the areaNegocio
	 */
	public int getAreaNegocio() {
		return areaNegocio;
	}
	/**
	 * @param areaNegocio the areaNegocio to set
	 */
	public void setAreaNegocio(int areaNegocio) {
		this.areaNegocio = areaNegocio;
	}
	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the usuarioCreacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	/**
	 * @param usuarioCreacion the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	
	
}
