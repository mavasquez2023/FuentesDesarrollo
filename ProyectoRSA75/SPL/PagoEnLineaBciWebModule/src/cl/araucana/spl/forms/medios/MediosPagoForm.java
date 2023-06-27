package cl.araucana.spl.forms.medios;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class MediosPagoForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private List medios;
	private List folios;
	private String glosa;
	private String urlRetorno;
	private String codSistema;
	private String xml;
	private String vector;
	private BigDecimal montoTotal;

	public String getCodSistema() {
		return codSistema;
	}
	public void setCodSistema(String codSistema) {
		this.codSistema = codSistema;
	}
	public List getFolios() {
		return folios;
	}
	public void setFolios(List folios) {
		this.folios = folios;
	}
	public String getGlosa() {
		return glosa;
	}
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}
	public List getMedios() {
		return medios;
	}
	public void setMedios(List medios) {
		this.medios = medios;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public String getVector() {
		return vector;
	}
	public void setVector(String vector) {
		this.vector = vector;
	}
	public String getUrlRetorno() {
		return urlRetorno;
	}
	public void setUrlRetorno(String urlRetorno) {
		this.urlRetorno = urlRetorno;
	}
	public BigDecimal getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}
	
}
