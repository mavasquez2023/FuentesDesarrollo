package cl.laaraucana.boletaelectronica.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(OrigenBoleta.class)
@Table(name = "DTF630", schema = "DTDTA")
public class OrigenBoleta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NUMBOL")
	private long NUMBOL;
	@Id
	@Column(name = "FOLIO")
	private int FOLIO;
	@Column(name = "TIPDOC")
	private int TIPDOC;
	@Column(name = "FECEMI")
	private String FECEMI;
	@Column(name = "ESTADO")
	private String ESTADO;
	@Column(name = "RUTREC")
	private String RUTREC;
	@Column(name = "NOMREC")
	private String NOMREC;
	@Column(name = "MONTOT")
	private long MONTOT;
	@Column(name = "CODITE")
	private int CODITE;
	@Column(name = "NOMITE")
	private String NOMITE;
	@Column(name = "PREUNI")
	private long PREUNI;
	@Column(name = "VALLIN")
	private long VALLIN;
	@Column(name = "USRCRT")
	private String USRCRT;
	@Column(name = "FECCRT")
	private int FECCRT;
	@Column(name = "HORCRT")
	private int HORCRT;
	@Column(name = "USRMOD")
	private String USRMOD;
	@Column(name = "FECMOD")
	private int FECMOD;
	@Column(name = "HORMOD")
	private int HORMOD;

	public int getFOLIO() {
		return FOLIO;
	}

	public void setFOLIO(int fOLIO) {
		FOLIO = fOLIO;
	}

	public int getTIPDOC() {
		return TIPDOC;
	}

	public void setTIPDOC(int tIPDOC) {
		TIPDOC = tIPDOC;
	}

	public long getNUMBOL() {
		return NUMBOL;
	}

	public void setNUMBOL(long nUMBOL) {
		NUMBOL = nUMBOL;
	}

	public String getFECEMI() {
		return FECEMI;
	}

	public void setFECEMI(String fECEMI) {
		FECEMI = fECEMI;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public String getRUTREC() {
		return RUTREC;
	}

	public void setRUTREC(String rUTREC) {
		RUTREC = rUTREC;
	}

	public String getNOMREC() {
		return NOMREC;
	}

	public void setNOMREC(String nOMREC) {
		NOMREC = nOMREC;
	}

	public long getMONTOT() {
		return MONTOT;
	}

	public void setMONTOT(long mONTOT) {
		MONTOT = mONTOT;
	}

	public int getCODITE() {
		return CODITE;
	}

	public void setCODITE(int cODITE) {
		CODITE = cODITE;
	}

	public String getNOMITE() {
		return NOMITE;
	}

	public void setNOMITE(String nOMITE) {
		NOMITE = nOMITE;
	}

	public long getPREUNI() {
		return PREUNI;
	}

	public void setPREUNI(long pREUNI) {
		PREUNI = pREUNI;
	}

	public long getVALLIN() {
		return VALLIN;
	}

	public void setVALLIN(long vALLIN) {
		VALLIN = vALLIN;
	}

	public String getUSRCRT() {
		return USRCRT;
	}

	public void setUSRCRT(String uSRCRT) {
		USRCRT = uSRCRT;
	}

	public int getFECCRT() {
		return FECCRT;
	}

	public void setFECCRT(int fECCRT) {
		FECCRT = fECCRT;
	}

	public int getHORCRT() {
		return HORCRT;
	}

	public void setHORCRT(int hORCRT) {
		HORCRT = hORCRT;
	}

	public String getUSRMOD() {
		return USRMOD;
	}

	public void setUSRMOD(String uSRMOD) {
		USRMOD = uSRMOD;
	}

	public int getFECMOD() {
		return FECMOD;
	}

	public void setFECMOD(int fECMOD) {
		FECMOD = fECMOD;
	}

	public int getHORMOD() {
		return HORMOD;
	}

	public void setHORMOD(int hORMOD) {
		HORMOD = hORMOD;
	}

}
