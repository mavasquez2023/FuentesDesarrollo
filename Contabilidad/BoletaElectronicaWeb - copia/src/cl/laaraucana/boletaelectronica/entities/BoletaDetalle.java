package cl.laaraucana.boletaelectronica.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BOLEDETA", schema = "DTDTA")
public class BoletaDetalle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDDET")
	private long iddet;
	@Column(name = "CODITEM")
	private String CODITEM;
	@Column(name = "NOMITEM")
	private String NOMITEM;
	@Column(name = "FOLDOC")
	private long FOLDOC;
	@Column(name = "CANTIDAD")
	private long CANTIDAD;
	@Column(name = "UNIMED")
	private String UNIMED;
	@Column(name = "PRECUNIT")
	private long PRECUNIT;
	@Column(name = "VALLINDET")
	private long VALLINDET;
	@Column(name = "CODCAJ")
	private String CODCAJ;
	@Column(name = "TIMELECTR")
	private String TIMELECTR;
	@ManyToOne
	@JoinColumn(name = "IDBASE")
	private BoletaBase boletaBase;

	public long getIddet() {
		return iddet;
	}

	public void setIddet(long iddet) {
		this.iddet = iddet;
	}

	public String getCODITEM() {
		return CODITEM;
	}

	public void setCODITEM(String cODITEM) {
		CODITEM = cODITEM;
	}

	public String getNOMITEM() {
		return NOMITEM;
	}

	public void setNOMITEM(String nOMITEM) {
		NOMITEM = nOMITEM;
	}

	public long getCANTIDAD() {
		return CANTIDAD;
	}

	public void setCANTIDAD(long cANTIDAD) {
		CANTIDAD = cANTIDAD;
	}

	public String getUNIMED() {
		return UNIMED;
	}

	public void setUNIMED(String uNIMED) {
		UNIMED = uNIMED;
	}

	public long getPRECUNIT() {
		return PRECUNIT;
	}

	public void setPRECUNIT(long pRECUNIT) {
		PRECUNIT = pRECUNIT;
	}

	public long getVALLINDET() {
		return VALLINDET;
	}

	public void setVALLINDET(long vALLINDET) {
		VALLINDET = vALLINDET;
	}

	public String getCODCAJ() {
		return CODCAJ;
	}

	public void setCODCAJ(String cODCAJ) {
		CODCAJ = cODCAJ;
	}

	public String getTIMELECTR() {
		return TIMELECTR;
	}

	public void setTIMELECTR(String tIMELECTR) {
		TIMELECTR = tIMELECTR;
	}

	public long getFOLDOC() {
		return FOLDOC;
	}

	public void setFOLDOC(long fOLDOC) {
		FOLDOC = fOLDOC;
	}

	public BoletaBase getBoletaBase() {
		return boletaBase;
	}

	public void setBoletaBase(BoletaBase boletaBase) {
		this.boletaBase = boletaBase;
	}

}
