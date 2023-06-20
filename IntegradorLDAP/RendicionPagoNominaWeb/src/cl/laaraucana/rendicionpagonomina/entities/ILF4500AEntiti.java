package cl.laaraucana.rendicionpagonomina.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ILF4500A", schema = "LIEXP")
public class ILF4500AEntiti implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FOLBCO")
	private String FOLBCO;
	@Column(name = "RUTAFI")
	private String RUTAFI;
	@Column(name = "FECING")
	private String FECING;
	@Column(name = "FOLNOM")
	private long FOLNOM;
	@Column(name = "CODREG")
	private String CODREG;
	@Column(name = "DVAFI")
	private String DVAFI;
	@Column(name = "NOMAFI")
	private String NOMAFI;
	@Column(name = "APPAFI")
	private String APPAFI;
	@Column(name = "APMAFI")
	private String APMAFI;
	@Column(name = "FORPAG")
	private int FORPAG;
	@Column(name = "CODBAN")
	private int CODBAN;
	@Column(name = "CUENTA")
	private long CUENTA;
	@Column(name = "MONTO")
	private long MONTO;
	@Column(name = "EMAIL")
	private String EMAIL;
	@Column(name = "CODEMP")
	private long CODEMP;
	@Column(name = "CODSUC")
	private int CODSUC;
	@Column(name = "SECFIN")
	private int SECFIN;
	@Column(name = "GLOSVV")
	private String GLOSVV;
	@Column(name = "GLOS01")
	private String GLOS01;
	@Column(name = "DATO01")
	private String DATO01;
	@Column(name = "INDD01")
	private String INDD01;
	@Column(name = "GLOS02")
	private String GLOS02;
	@Column(name = "DATO02")
	private String DATO02;
	@Column(name = "INDD02")
	private String INDD02;
	@Column(name = "GLOS03")
	private String GLOS03;
	@Column(name = "DATO03")
	private String DATO03;
	@Column(name = "INDD03")
	private String INDD03;
	@Column(name = "GLOS04")
	private String GLOS04;
	@Column(name = "DATO04")
	private String DATO04;
	@Column(name = "INDD04")
	private String INDD04;
	@Column(name = "GLOS05")
	private String GLOS05;
	@Column(name = "DATO05")
	private String DATO05;
	@Column(name = "INDD05")
	private String INDD05;
	@Column(name = "GLOS06")
	private String GLOS06;
	@Column(name = "DATO06")
	private String DATO06;
	@Column(name = "INDD06")
	private String INDD06;
	@Column(name = "GLOS07")
	private String GLOS07;
	@Column(name = "DATO07")
	private String DATO07;
	@Column(name = "INDD07")
	private String INDD07;
	@Column(name = "CODSEG")
	private int CODSEG;
	@Column(name = "TIPSUB")
	private int TIPSUB;
	@Column(name = "TIPCON")
	private int TIPCON;
	@Column(name = "ESTNOM")
	private String ESTNOM;
	@Column(name = "FECCOB")
	private long FECCOB;
	@Column(name = "ESTCOB")
	private String ESTCOB;
	@Column(name = "FECCRE")
	private long FECCRE;
	@Column(name = "HORCRE")
	private int HORCRE;
	@Column(name = "FECACT")
	private long FECACT;
	@Column(name = "HORACT")
	private int HORACT;
	@Column(name = "USRCRE")
	private String USRCRE;
	@Column(name = "USRACT")
	private String USRACT;

	public String getFECING() {
		return FECING;
	}

	public void setFECING(String fECING) {
		FECING = fECING;
	}

	public long getFOLNOM() {
		return FOLNOM;
	}

	public void setFOLNOM(long fOLNOM) {
		FOLNOM = fOLNOM;
	}

	public String getCODREG() {
		return CODREG;
	}

	public void setCODREG(String cODREG) {
		CODREG = cODREG;
	}

	public String getRUTAFI() {
		return RUTAFI;
	}

	public void setRUTAFI(String rUTAFI) {
		RUTAFI = rUTAFI;
	}

	public String getDVAFI() {
		return DVAFI;
	}

	public void setDVAFI(String dVAFI) {
		DVAFI = dVAFI;
	}

	public String getNOMAFI() {
		return NOMAFI;
	}

	public void setNOMAFI(String nOMAFI) {
		NOMAFI = nOMAFI;
	}

	public String getAPPAFI() {
		return APPAFI;
	}

	public void setAPPAFI(String aPPAFI) {
		APPAFI = aPPAFI;
	}

	public String getAPMAFI() {
		return APMAFI;
	}

	public void setAPMAFI(String aPMAFI) {
		APMAFI = aPMAFI;
	}

	public int getFORPAG() {
		return FORPAG;
	}

	public void setFORPAG(int fORPAG) {
		FORPAG = fORPAG;
	}

	public int getCODBAN() {
		return CODBAN;
	}

	public void setCODBAN(int cODBAN) {
		CODBAN = cODBAN;
	}

	public long getCUENTA() {
		return CUENTA;
	}

	public void setCUENTA(long cUENTA) {
		CUENTA = cUENTA;
	}

	public long getMONTO() {
		return MONTO;
	}

	public void setMONTO(long mONTO) {
		MONTO = mONTO;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public long getCODEMP() {
		return CODEMP;
	}

	public void setCODEMP(long cODEMP) {
		CODEMP = cODEMP;
	}

	public int getCODSUC() {
		return CODSUC;
	}

	public void setCODSUC(int cODSUC) {
		CODSUC = cODSUC;
	}

	public int getSECFIN() {
		return SECFIN;
	}

	public void setSECFIN(int sECFIN) {
		SECFIN = sECFIN;
	}

	public String getGLOSVV() {
		return GLOSVV;
	}

	public void setGLOSVV(String gLOSVV) {
		GLOSVV = gLOSVV;
	}

	public String getGLOS01() {
		return GLOS01;
	}

	public void setGLOS01(String gLOS01) {
		GLOS01 = gLOS01;
	}

	public String getDATO01() {
		return DATO01;
	}

	public void setDATO01(String dATO01) {
		DATO01 = dATO01;
	}

	public String getINDD01() {
		return INDD01;
	}

	public void setINDD01(String iNDD01) {
		INDD01 = iNDD01;
	}

	public String getGLOS02() {
		return GLOS02;
	}

	public void setGLOS02(String gLOS02) {
		GLOS02 = gLOS02;
	}

	public String getDATO02() {
		return DATO02;
	}

	public void setDATO02(String dATO02) {
		DATO02 = dATO02;
	}

	public String getINDD02() {
		return INDD02;
	}

	public void setINDD02(String iNDD02) {
		INDD02 = iNDD02;
	}

	public String getGLOS03() {
		return GLOS03;
	}

	public void setGLOS03(String gLOS03) {
		GLOS03 = gLOS03;
	}

	public String getDATO03() {
		return DATO03;
	}

	public void setDATO03(String dATO03) {
		DATO03 = dATO03;
	}

	public String getINDD03() {
		return INDD03;
	}

	public void setINDD03(String iNDD03) {
		INDD03 = iNDD03;
	}

	public String getGLOS04() {
		return GLOS04;
	}

	public void setGLOS04(String gLOS04) {
		GLOS04 = gLOS04;
	}

	public String getDATO04() {
		return DATO04;
	}

	public void setDATO04(String dATO04) {
		DATO04 = dATO04;
	}

	public String getINDD04() {
		return INDD04;
	}

	public void setINDD04(String iNDD04) {
		INDD04 = iNDD04;
	}

	public String getGLOS05() {
		return GLOS05;
	}

	public void setGLOS05(String gLOS05) {
		GLOS05 = gLOS05;
	}

	public String getDATO05() {
		return DATO05;
	}

	public void setDATO05(String dATO05) {
		DATO05 = dATO05;
	}

	public String getINDD05() {
		return INDD05;
	}

	public void setINDD05(String iNDD05) {
		INDD05 = iNDD05;
	}

	public String getGLOS06() {
		return GLOS06;
	}

	public void setGLOS06(String gLOS06) {
		GLOS06 = gLOS06;
	}

	public String getDATO06() {
		return DATO06;
	}

	public void setDATO06(String dATO06) {
		DATO06 = dATO06;
	}

	public String getINDD06() {
		return INDD06;
	}

	public void setINDD06(String iNDD06) {
		INDD06 = iNDD06;
	}

	public String getGLOS07() {
		return GLOS07;
	}

	public void setGLOS07(String gLOS07) {
		GLOS07 = gLOS07;
	}

	public String getDATO07() {
		return DATO07;
	}

	public void setDATO07(String dATO07) {
		DATO07 = dATO07;
	}

	public String getINDD07() {
		return INDD07;
	}

	public void setINDD07(String iNDD07) {
		INDD07 = iNDD07;
	}

	public int getCODSEG() {
		return CODSEG;
	}

	public void setCODSEG(int cODSEG) {
		CODSEG = cODSEG;
	}

	public String getFOLBCO() {
		return FOLBCO;
	}

	public void setFOLBCO(String fOLBCO) {
		FOLBCO = fOLBCO;
	}

	public int getTIPSUB() {
		return TIPSUB;
	}

	public void setTIPSUB(int tIPSUB) {
		TIPSUB = tIPSUB;
	}

	public int getTIPCON() {
		return TIPCON;
	}

	public void setTIPCON(int tIPCON) {
		TIPCON = tIPCON;
	}

	public String getESTNOM() {
		return ESTNOM;
	}

	public void setESTNOM(String eSTNOM) {
		ESTNOM = eSTNOM;
	}

	public long getFECCOB() {
		return FECCOB;
	}

	public void setFECCOB(long fECCOB) {
		FECCOB = fECCOB;
	}

	public String getESTCOB() {
		return ESTCOB;
	}

	public void setESTCOB(String eSTCOB) {
		ESTCOB = eSTCOB;
	}

	public long getFECCRE() {
		return FECCRE;
	}

	public void setFECCRE(long fECCRE) {
		FECCRE = fECCRE;
	}

	public int getHORCRE() {
		return HORCRE;
	}

	public void setHORCRE(int hORCRE) {
		HORCRE = hORCRE;
	}

	public long getFECACT() {
		return FECACT;
	}

	public void setFECACT(long fECACT) {
		FECACT = fECACT;
	}

	public int getHORACT() {
		return HORACT;
	}

	public void setHORACT(int hORACT) {
		HORACT = hORACT;
	}

	public String getUSRCRE() {
		return USRCRE;
	}

	public void setUSRCRE(String uSRCRE) {
		USRCRE = uSRCRE;
	}

	public String getUSRACT() {
		return USRACT;
	}

	public void setUSRACT(String uSRACT) {
		USRACT = uSRACT;
	}

}
