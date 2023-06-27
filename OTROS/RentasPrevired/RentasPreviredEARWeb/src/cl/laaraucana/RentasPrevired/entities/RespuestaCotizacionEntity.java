package cl.laaraucana.RentasPrevired.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NPRCO")
public class RespuestaCotizacionEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	@Column(name = "RUTTRAB", length = 9)
	private int RUTTRAB;
	@Column(name = "TIPOREG", length = 2)
	private String TIPOREG;
	@Column(name = "PERIODO", length = 6)
	private String PERIODO;
	@Column(name = "TIPOCOT", length = 1)
	private String TIPOCOT;
	@Column(name = "RENTIM", length = 10)
	private int RENTIM;
	@Column(name = "RUTEMP", length = 9)
	private int RUTEMP;
	@Column(name = "DVEMP", length = 1)
	private String DVEMP;
	@Column(name = "CODINST", length = 4)
	private int CODINST;
	@Column(name = "TIPOPAG", length = 1)
	private String TIPOPAG;
	@Column(name = "DVTRAB", length = 1)
	private String DVTRAB;
	@Column(name = "CODMOVPER", length = 2)
	private int CODMOVPER;
	@Column(name = "FECINMOV", length = 10)
	private String FECINMOV;
	@Column(name = "FECTERMOV", length = 10)
	private String FECTERMOV;
	@Column(name = "MONCOTTRAB", length = 10)
	private int MONCOTTRAB;
	@Column(name = "MONCOTEMP", length = 10)
	private int MONCOTEMP;
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name = "RUTTRAB", referencedColumnName = "RUTAFI")
	private RespuestaAfiliacionEntity respuestaAfiliadoEntity;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getTIPOREG() {
		return TIPOREG;
	}

	public void setTIPOREG(String tIPOREG) {
		TIPOREG = tIPOREG;
	}

	public String getPERIODO() {
		return PERIODO;
	}

	public void setPERIODO(String pERIODO) {
		PERIODO = pERIODO;
	}

	public String getTIPOCOT() {
		return TIPOCOT;
	}

	public void setTIPOCOT(String tIPOCOT) {
		TIPOCOT = tIPOCOT;
	}

	public int getRENTIM() {
		return RENTIM;
	}

	public void setRENTIM(int rENTIM) {
		RENTIM = rENTIM;
	}

	public int getRUTEMP() {
		return RUTEMP;
	}

	public void setRUTEMP(int rUTEMP) {
		RUTEMP = rUTEMP;
	}

	public String getDVEMP() {
		return DVEMP;
	}

	public void setDVEMP(String dVEMP) {
		DVEMP = dVEMP;
	}

	public int getCODINST() {
		return CODINST;
	}

	public void setCODINST(int cODINST) {
		CODINST = cODINST;
	}

	public String getTIPOPAG() {
		return TIPOPAG;
	}

	public void setTIPOPAG(String tIPOPAG) {
		TIPOPAG = tIPOPAG;
	}

	public int getRUTTRAB() {
		return RUTTRAB;
	}

	public void setRUTTRAB(int rUTTRAB) {
		RUTTRAB = rUTTRAB;
	}

	public String getDVTRAB() {
		return DVTRAB;
	}

	public void setDVTRAB(String dVTRAB) {
		DVTRAB = dVTRAB;
	}

	public int getCODMOVPER() {
		return CODMOVPER;
	}

	public void setCODMOVPER(int cODMOVPER) {
		CODMOVPER = cODMOVPER;
	}

	public String getFECINMOV() {
		return FECINMOV;
	}

	public void setFECINMOV(String fECINMOV) {
		FECINMOV = fECINMOV;
	}

	public String getFECTERMOV() {
		return FECTERMOV;
	}

	public void setFECTERMOV(String fECTERMOV) {
		FECTERMOV = fECTERMOV;
	}

	public int getMONCOTTRAB() {
		return MONCOTTRAB;
	}

	public void setMONCOTTRAB(int mONCOTTRAB) {
		MONCOTTRAB = mONCOTTRAB;
	}

	public int getMONCOTEMP() {
		return MONCOTEMP;
	}

	public void setMONCOTEMP(int mONCOTEMP) {
		MONCOTEMP = mONCOTEMP;
	}

	public RespuestaAfiliacionEntity getRespuestaAfiliadoEntity() {
		return respuestaAfiliadoEntity;
	}

	public void setRespuestaAfiliadoEntity(RespuestaAfiliacionEntity respuestaAfiliadoEntity) {
		this.respuestaAfiliadoEntity = respuestaAfiliadoEntity;
	}

}
