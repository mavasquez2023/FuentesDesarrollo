package cl.laaraucana.rendicionpagonomina.ibatis.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


public class ProductoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoProducto;
	
	private int codigoConvenio;
	
	private String descripcionProducto;

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
	 * @return the codigoConvenio
	 */
	public int getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @param codigoConvenio the codigoConvenio to set
	 */
	public void setCodigoConvenio(int codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}

	/**
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * @param descripcionProducto the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	
	
}
