package cl.laaraucana.botonpago.web.spl.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(
		propOrder = { "glosa", "pagador", "correlativo", "fecha", "urlRetorno", "urlNotificacion", "rutCliente", "bancos", "folios" })
public class Pago {

	private String glosa;
	private String pagador;
	private String correlativo;
	private String fecha;
	private String urlRetorno;
	private String urlNotificacion;
	private String rutCliente;
	private Banco bancos;
	private Folios folios;

	public String getGlosa() {
		return glosa;
	}

	@XmlElement
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public String getPagador() {
		return pagador;
	}

	@XmlElement
	public void setPagador(String pagador) {
		this.pagador = pagador;
	}

	public String getCorrelativo() {
		return correlativo;
	}

	@XmlElement
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}

	public String getFecha() {
		return fecha;
	}

	@XmlElement
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getUrlRetorno() {
		return urlRetorno;
	}

	@XmlElement
	public void setUrlRetorno(String urlRetorno) {
		this.urlRetorno = urlRetorno;
	}

	public String getUrlNotificacion() {
		return urlNotificacion;
	}

	@XmlElement
	public void setUrlNotificacion(String urlNotificacion) {
		this.urlNotificacion = urlNotificacion;
	}

	public Banco getBancos() {
		return bancos;
	}

	@XmlElement
	public void setBancos(Banco bancos) {
		this.bancos = bancos;
	}

	public Folios getFolios() {
		return folios;
	}

	@XmlElement
	public void setFolios(Folios folios) {
		this.folios = folios;
	}
	
	public String getRutCliente() {
		return rutCliente;
	}

	@XmlElement
	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}
}
