package cl.laaraucana.copagocredito.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MANDATO", schema = "SPLDTA")
public class MandatosEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID_MANDATO")
	private long ID_MANDATO;
	@Column(name = "RUTAFI")
	private long RUTAFI;
	@Column(name = "DVAFI")
	private String DVAFI;
	@Column(name = "NOMBRE")
	private String NOMBRE;
	@Column(name = "CELULAR")
	private String CELULAR;
	@Column(name = "TELEFONO")
	private String TELEFONO;
	@Column(name = "EMAIL")
	private String EMAIL;
	@Column(name = "CODBANCO")
	private int CODBANCO;
	@Column(name = "NUMCUENTA")
	private String NUMCUENTA;
	@Column(name = "ID_TIPCTA")
	private int ID_TIPCTA;
	@Column(name = "ID_TIPPRO")
	private int ID_TIPPRO;
	@Column(name = "FECHAVIG")
	private Date FECHAVIG;
	@Column(name = "FECHATER")
	private String FECHATER;
	@Column(name = "OBF002")
	private Date OBF002;
	@Column(name = "OBF003")
	private String OBF003;
	@Column(name = "SAJKM94")
	private String SAJKM94;

	public long getID_MANDATO() {
		return ID_MANDATO;
	}

	public void setID_MANDATO(long iD_MANDATO) {
		ID_MANDATO = iD_MANDATO;
	}

	public long getRUTAFI() {
		return RUTAFI;
	}

	public void setRUTAFI(long rUTAFI) {
		RUTAFI = rUTAFI;
	}

	public String getDVAFI() {
		return DVAFI;
	}

	public void setDVAFI(String dVAFI) {
		DVAFI = dVAFI;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public String getCELULAR() {
		return CELULAR;
	}

	public void setCELULAR(String cELULAR) {
		CELULAR = cELULAR;
	}

	public String getTELEFONO() {
		return TELEFONO;
	}

	public void setTELEFONO(String tELEFONO) {
		TELEFONO = tELEFONO;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public int getCODBANCO() {
		return CODBANCO;
	}

	public void setCODBANCO(int cODBANCO) {
		CODBANCO = cODBANCO;
	}

	public String getNUMCUENTA() {
		return NUMCUENTA;
	}

	public void setNUMCUENTA(String nUMCUENTA) {
		NUMCUENTA = nUMCUENTA;
	}

	public int getID_TIPCTA() {
		return ID_TIPCTA;
	}

	public void setID_TIPCTA(int iD_TIPCTA) {
		ID_TIPCTA = iD_TIPCTA;
	}

	public int getID_TIPPRO() {
		return ID_TIPPRO;
	}

	public void setID_TIPPRO(int iD_TIPPRO) {
		ID_TIPPRO = iD_TIPPRO;
	}

	public Date getFECHAVIG() {
		return FECHAVIG;
	}

	public void setFECHAVIG(Date fECHAVIG) {
		FECHAVIG = fECHAVIG;
	}

	public String getFECHATER() {
		return FECHATER;
	}

	public void setFECHATER(String fECHATER) {
		FECHATER = fECHATER;
	}

	public Date getOBF002() {
		return OBF002;
	}

	public void setOBF002(Date oBF002) {
		OBF002 = oBF002;
	}

	public String getOBF003() {
		return OBF003;
	}

	public void setOBF003(String oBF003) {
		OBF003 = oBF003;
	}

	public String getSAJKM94() {
		return SAJKM94;
	}

	public void setSAJKM94(String sAJKM94) {
		SAJKM94 = sAJKM94;
	}

}
