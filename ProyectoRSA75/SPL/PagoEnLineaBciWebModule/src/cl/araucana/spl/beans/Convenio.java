package cl.araucana.spl.beans;

import java.math.BigDecimal;

public class Convenio {
	private BigDecimal id;
	private MedioPago medio;
	private String codigo;
	private String urlCgi;
	private BigDecimal codigoServicioRecaudacion;
	private String ctaCorriente;
	
	public String toString() {
		return new StringBuffer("[CONVENIO::id=").append(id)
			.append(",codigo=").append(codigo)
			.append(",medio=").append(medio)
			.append(",urlCgi=").append(urlCgi)
			.append(",srvrec=").append(codigoServicioRecaudacion)
			.append("]").toString();
	}	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public MedioPago getMedio() {
		return medio;
	}
	public void setMedio(MedioPago medio) {
		this.medio = medio;
	}
	public String getCodigoBanco() {
		return medio.getCodigo();
	}
	public String getUrlInicio() {
		return medio.getUrlIniPago();
	}
	public String getUrlCgi() {
		return urlCgi;
	}
	public void setUrlCgi(String urlCgi) {
		this.urlCgi = urlCgi;
	}
	public BigDecimal getCodigoServicioRecaudacion() {
		return codigoServicioRecaudacion;
	}
	public void setCodigoServicioRecaudacion(BigDecimal codigoServicioRecaudacion) {
		this.codigoServicioRecaudacion = codigoServicioRecaudacion;
	}

	public void setCtaCorriente(String ctaCorriente) {
		this.ctaCorriente = ctaCorriente;
	}

	public String getCtaCorriente() {
		return ctaCorriente;
	}
}
