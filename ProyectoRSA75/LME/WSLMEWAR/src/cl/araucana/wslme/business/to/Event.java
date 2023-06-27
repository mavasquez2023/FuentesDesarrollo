package cl.araucana.wslme.business.to;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Event implements Serializable {

	private static final long serialVersionUID = -5017085210168905048L;

	private Timestamp fechEven;
	private String inCodOpe;
	private String inPassOpe;
	private String inCodCcaf;
	private Double inRutAfil;
	private String inRutDv;
	private Double outEstado;
	private String outGloest;
	private List empresas;
	private Timestamp fechEndEven;

	public Event() {
		super();
	}

	public Event(Timestamp fechEven, String inCodOpe, String inPassOpe,
			String inCodCcaf, Double inRutAfil, String inRutDv,
			Double outEstado, String outGloest, List empresas, Timestamp fechEndEven) {
		super();
		this.fechEven = fechEven;
		this.inCodOpe = inCodOpe;
		this.inPassOpe = inPassOpe;
		this.inCodCcaf = inCodCcaf;
		this.inRutAfil = inRutAfil;
		this.inRutDv = inRutDv;
		this.outEstado = outEstado;
		this.outGloest = outGloest;
		this.empresas = empresas;
		this.fechEndEven = fechEndEven;
	}

	public Timestamp getFechEven() {
		return fechEven;
	}

	public void setFechEven(Timestamp fechEven) {
		this.fechEven = fechEven;
	}

	public String getInCodOpe() {
		return inCodOpe;
	}

	public void setInCodOpe(String inCodOpe) {
		this.inCodOpe = inCodOpe;
	}

	public String getInPassOpe() {
		return inPassOpe;
	}

	public void setInPassOpe(String inPassOpe) {
		this.inPassOpe = inPassOpe;
	}

	public String getInCodCcaf() {
		return inCodCcaf;
	}

	public void setInCodCcaf(String inCodCcaf) {
		this.inCodCcaf = inCodCcaf;
	}

	public Double getInRutAfil() {
		return inRutAfil;
	}

	public void setInRutAfil(Double inRutAfil) {
		this.inRutAfil = inRutAfil;
	}

	public String getInRutDv() {
		return inRutDv;
	}

	public void setInRutDv(String inRutDv) {
		this.inRutDv = inRutDv;
	}

	public Double getOutEstado() {
		return outEstado;
	}

	public void setOutEstado(Double outEstado) {
		this.outEstado = outEstado;
	}

	public String getOutGloest() {
		return outGloest;
	}

	public void setOutGloest(String outGloest) {
		this.outGloest = outGloest;
	}

	public List getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List empresas) {
		this.empresas = empresas;
	}
	
	public Timestamp getFechEndEven() {
		return fechEndEven;
	}

	public void setFechEndEven(Timestamp fechEndEven) {
		this.fechEndEven = fechEndEven;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
