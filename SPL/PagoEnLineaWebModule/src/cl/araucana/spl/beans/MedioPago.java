package cl.araucana.spl.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import cl.araucana.spl.base.Constants;


public class MedioPago implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BigDecimal id;
	private String codigo 		= null;
	private String descripcion	= null;
	private String imagen		= null;
	private String urlIniPago	= null;
	private BigDecimal activo;
	private int codigoBanco;
	private String urlRetornoPago	= null;
	private String urlNotificacionPago	= null;
	private String tipoNotificacion; // 0 = Batch, 1 = online
	
	public String toString() {
		return new StringBuffer("[MEDIO::id=").append(id)
			.append(",codigo=").append(codigo)
			.append(",descripcion=").append(descripcion)
			.append(",imagen=").append(imagen)
			.append(",urlIniPago=").append(urlIniPago)
			.append(",codigoBanco=").append(codigoBanco)
			.append(",urlRetornoPago=").append(urlRetornoPago)
			.append(",urlNotificacionPago=").append(urlNotificacionPago)
			.append("]").toString();
	}	
	

	public int getCodigoBanco() {
		return codigoBanco;
	}


	public void setCodigoBanco(int codigoBanco) {
		this.codigoBanco = codigoBanco;
	}


	public boolean esMedioActivo() {
		return Constants.MEDIO_ACTIVO.equals(activo);
	}

	public BigDecimal getActivo() {
		return activo;
	}
	public void setActivo(BigDecimal activo) {
		this.activo = activo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getUrlIniPago() {
		return urlIniPago;
	}
	public void setUrlIniPago(String urlIniPago) {
		this.urlIniPago = urlIniPago;
	}
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}


	public String getUrlNotificacionPago() {
		return urlNotificacionPago;
	}


	public void setUrlNotificacionPago(String urlNotificacionPago) {
		this.urlNotificacionPago = urlNotificacionPago;
	}


	public String getUrlRetornoPago() {
		return urlRetornoPago;
	}


	public void setUrlRetornoPago(String urlRetornoPago) {
		this.urlRetornoPago = urlRetornoPago;
	}


	public String getTipoNotificacion() {
		return tipoNotificacion;
	}


	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}
}
