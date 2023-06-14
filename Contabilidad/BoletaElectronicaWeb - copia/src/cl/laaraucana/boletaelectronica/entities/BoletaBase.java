package cl.laaraucana.boletaelectronica.entities;

import java.io.Serializable;
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
@Table(name = "BOLECABE", schema = "DTDTA")
public class BoletaBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDBASE")
	private long idbase;
	@Column(name = "VERSION")
	private String VERSION;
	@Column(name = "TIPDOC")
	private int TIPDOC;
	@Column(name = "FOLDOC")
	private long FOLDOC;
	@Column(name = "EMICONT")
	private String EMICONT;
	@Column(name = "INDICSERV")
	private int INDICSERV;
	@Column(name = "RUTEM")
	private String RUTEM;
	@Column(name = "RAZONSOCEM")
	private String RAZONSOCEM;
	@Column(name = "GIRONEGEM")
	private String GIRONEGEM;
	@Column(name = "DIRORIGEN")
	private String DIRORIGEN;
	@Column(name = "COMORIGEN")
	private String COMORIGEN;
	@Column(name = "CIUORIGEN")
	private String CIUORIGEN;
	@Column(name = "RUTREC")
	private String RUTREC;
	@Column(name = "NOMREC")
	private String NOMREC;
	@Column(name = "MONTOEX")
	private long MONTOEX;
	@Column(name = "MONTOTAL")
	private long MONTOTAL;
	@Column(name = "FECCRE")
	private long FECCRE;
	@Column(name = "HORACRE")
	private String HORACRE;
	@Column(name = "FECMOD")
	private String FECMOD;
	@Column(name = "HORAMOD")
	private String HORAMOD;
	@Column(name = "MONTONETO")
	private long MONTONETO;
	@Column(name = "MONTOIVA")
	private long MONTOIVA;
	@Column(name = "ESTADO")
	private int ESTADO;
	@Column(name = "NUMBOL")
	private long NUMBOL;
	@Column(name = "URLACEPTA")
	private String URLACEPTA;
	@OneToMany(mappedBy = "boletaBase", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<BoletaDetalle> boletaDetalle;

	public long getIdbase() {
		return idbase;
	}

	public void setIdbase(long idbase) {
		this.idbase = idbase;
	}

	public String getVERSION() {
		return VERSION;
	}

	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}

	public int getTIPDOC() {
		return TIPDOC;
	}

	public void setTIPDOC(int tIPDOC) {
		TIPDOC = tIPDOC;
	}

	public long getFOLDOC() {
		return FOLDOC;
	}

	public void setFOLDOC(long fOLDOC) {
		FOLDOC = fOLDOC;
	}

	public String getEMICONT() {
		return EMICONT;
	}

	public void setEMICONT(String eMICONT) {
		EMICONT = eMICONT;
	}

	public int getINDICSERV() {
		return INDICSERV;
	}

	public void setINDICSERV(int iNDICSERV) {
		INDICSERV = iNDICSERV;
	}

	public String getRUTEM() {
		return RUTEM;
	}

	public void setRUTEM(String rUTEM) {
		RUTEM = rUTEM;
	}

	public String getRAZONSOCEM() {
		return RAZONSOCEM;
	}

	public void setRAZONSOCEM(String rAZONSOCEM) {
		RAZONSOCEM = rAZONSOCEM;
	}

	public String getGIRONEGEM() {
		return GIRONEGEM;
	}

	public void setGIRONEGEM(String gIRONEGEM) {
		GIRONEGEM = gIRONEGEM;
	}

	public String getDIRORIGEN() {
		return DIRORIGEN;
	}

	public void setDIRORIGEN(String dIRORIGEN) {
		DIRORIGEN = dIRORIGEN;
	}

	public String getCOMORIGEN() {
		return COMORIGEN;
	}

	public void setCOMORIGEN(String cOMORIGEN) {
		COMORIGEN = cOMORIGEN;
	}

	public String getCIUORIGEN() {
		return CIUORIGEN;
	}

	public void setCIUORIGEN(String cIUORIGEN) {
		CIUORIGEN = cIUORIGEN;
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

	public long getMONTOEX() {
		return MONTOEX;
	}

	public void setMONTOEX(long mONTOEX) {
		MONTOEX = mONTOEX;
	}

	public long getMONTOTAL() {
		return MONTOTAL;
	}

	public void setMONTOTAL(long mONTOTAL) {
		MONTOTAL = mONTOTAL;
	}

	public List<BoletaDetalle> getBoletaDetalle() {
		return boletaDetalle;
	}

	public void setBoletaDetalle(List<BoletaDetalle> boletaDetalle) {
		this.boletaDetalle = boletaDetalle;
	}

	public long getFECCRE() {
		return FECCRE;
	}

	public void setFECCRE(long fECCRE) {
		FECCRE = fECCRE;
	}

	public String getHORACRE() {
		return HORACRE;
	}

	public void setHORACRE(String hORACRE) {
		HORACRE = hORACRE;
	}

	public String getFECMOD() {
		return FECMOD;
	}

	public void setFECMOD(String fECMOD) {
		FECMOD = fECMOD;
	}

	public String getHORAMOD() {
		return HORAMOD;
	}

	public void setHORAMOD(String hORAMOD) {
		HORAMOD = hORAMOD;
	}

	public int getESTADO() {
		return ESTADO;
	}

	public void setESTADO(int eSTADO) {
		ESTADO = eSTADO;
	}

	public long getNUMBOL() {
		return NUMBOL;
	}

	public void setNUMBOL(long nUMBOL) {
		NUMBOL = nUMBOL;
	}

	public long getMONTONETO() {
		return MONTONETO;
	}

	public void setMONTONETO(long mONTONETO) {
		MONTONETO = mONTONETO;
	}

	public long getMONTOIVA() {
		return MONTOIVA;
	}

	public void setMONTOIVA(long mONTOIVA) {
		MONTOIVA = mONTOIVA;
	}

	public String getURLACEPTA() {
		return URLACEPTA;
	}

	public void setURLACEPTA(String uRLACEPTA) {
		URLACEPTA = uRLACEPTA;
	}

}
