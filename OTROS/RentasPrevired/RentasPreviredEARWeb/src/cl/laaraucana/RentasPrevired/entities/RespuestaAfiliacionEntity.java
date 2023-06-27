package cl.laaraucana.RentasPrevired.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "NPMRRA")
public class RespuestaAfiliacionEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// private long ID;
	@Column(name = "RUTAFI", length = 9)
	private int RUTAFI;
	@Column(name = "TIPOREG", length = 2)
	private String TIPOREG;
	@Column(name = "CODREAFI", length = 4)
	private int CODREAFI;
	@Column(name = "GLSRETAFI", length = 100)
	private String GLSRETAFI;
	@Column(name = "DVAFI", length = 1)
	private String DVAFI;
	@Column(name = "NOMBRES", length = 100)
	private String NOMBRES;
	@Column(name = "APEPAT", length = 50)
	private String APEPAT;
	@Column(name = "APEMAT", length = 50)
	private String APEMAT;
	@Column(name = "FECINSIS", length = 10)
	private String FECINSIS;
	@Column(name = "CODAFPVIG", length = 4)
	private int CODAFPVIG;
	@Column(name = "FECINAFP", length = 10)
	private String FECINAFP;
	@Column(name = "SITUAAFI", length = 1)
	private String SITUAAFI;
	@OneToMany(mappedBy = "respuestaAfiliadoEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<RespuestaCotizacionEntity> respuestaCotizacionEntity;

	public String getTIPOREG() {
		return TIPOREG;
	}

	public void setTIPOREG(String tIPOREG) {
		TIPOREG = tIPOREG;
	}

	public int getCODREAFI() {
		return CODREAFI;
	}

	public void setCODREAFI(int cODREAFI) {
		CODREAFI = cODREAFI;
	}

	public String getGLSRETAFI() {
		return GLSRETAFI;
	}

	public void setGLSRETAFI(String gLSRETAFI) {
		GLSRETAFI = gLSRETAFI;
	}

	public int getRUTAFI() {
		return RUTAFI;
	}

	public void setRUTAFI(int rUTAFI) {
		RUTAFI = rUTAFI;
	}

	public String getDVAFI() {
		return DVAFI;
	}

	public void setDVAFI(String dVAFI) {
		DVAFI = dVAFI;
	}

	public String getNOMBRES() {
		return NOMBRES;
	}

	public void setNOMBRES(String nOMBRES) {
		NOMBRES = nOMBRES;
	}

	public String getAPEPAT() {
		return APEPAT;
	}

	public void setAPEPAT(String aPEPAT) {
		APEPAT = aPEPAT;
	}

	public String getAPEMAT() {
		return APEMAT;
	}

	public void setAPEMAT(String aPEMAT) {
		APEMAT = aPEMAT;
	}

	public String getFECINSIS() {
		return FECINSIS;
	}

	public void setFECINSIS(String fECINSIS) {
		FECINSIS = fECINSIS;
	}

	public int getCODAFPVIG() {
		return CODAFPVIG;
	}

	public void setCODAFPVIG(int cODAFPVIG) {
		CODAFPVIG = cODAFPVIG;
	}

	public String getFECINAFP() {
		return FECINAFP;
	}

	public void setFECINAFP(String fECINAFP) {
		FECINAFP = fECINAFP;
	}

	public String getSITUAAFI() {
		return SITUAAFI;
	}

	public void setSITUAAFI(String sITUAAFI) {
		SITUAAFI = sITUAAFI;
	}

	public List<RespuestaCotizacionEntity> getRespuestaCotizacionEntity() {
		return respuestaCotizacionEntity;
	}

	public void setRespuestaCotizacionEntity(List<RespuestaCotizacionEntity> respuestaCotizacionEntity) {
		this.respuestaCotizacionEntity = respuestaCotizacionEntity;
	}

}
