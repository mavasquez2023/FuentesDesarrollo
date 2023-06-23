package cl.araucana.spl.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class WrapperXmlMedioPago implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String glosa 			= null;
	private String pagador 			= null;
	private String rutCliente       = null;	
	private String correlativo		= null;
	private String fecha			= null;
	private String urlRetorno		= null;
	private String urlNotificacion;
	private List mediosPagoBeans	= null;
	private List foliosBeans		= null;	
	
	public String toString(){
		StringBuffer sf = new StringBuffer("WrapperXmlMedioPago=[");
		
		sf.append("glosa=").append(glosa);
		sf.append(",pagador=").append(pagador);
		sf.append(",rutCliente=").append(rutCliente);
		sf.append(",correlativo=").append(correlativo);
		sf.append(",fecha=").append(fecha);
		sf.append(",urlRetorno=").append(urlRetorno);
		sf.append(",urlNotificacion=").append(urlNotificacion);
		sf.append(",mediosPagos=").append(mediosPagoBeans.toString());
		sf.append(",foliosBeans=").append(foliosBeans.toString());
		sf.append("]");
		
		return sf.toString();
	}
	
	
	public String getRutCliente() {
		return rutCliente;
	}
	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public List getFoliosBeans() {
		return foliosBeans;
	}
	public void setFoliosBeans(List foliosBeans) {
		this.foliosBeans = foliosBeans;
	}
	public String getGlosa() {
		return glosa;
	}
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}
	public List getMediosPagoBeans() {
		return mediosPagoBeans;
	}
	public void setMediosPagoBeans(List mediosPagoBeans) {
		this.mediosPagoBeans = mediosPagoBeans;
	}
	public String getPagador() {
		return pagador;
	}
	public void setPagador(String pagador) {
		this.pagador = pagador;
	}
	
	public String getUrlRetorno() {
		return urlRetorno;
	}
	public void setUrlRetorno(String urlRetorno) {
		this.urlRetorno = urlRetorno;
	}
	public BigDecimal getMontoTotal() {
		BigDecimal total = new BigDecimal(0);
		for (Iterator it = foliosBeans.iterator(); it.hasNext(); ) {
			Folio folio = (Folio) it.next();
			total = total.add(folio.getMonto());
		}
		return total;
	}
	public String getUrlNotificacion() {
		return urlNotificacion;
	}
	public void setUrlNotificacion(String urlNotificacion) {
		this.urlNotificacion = urlNotificacion;
	} 
}
