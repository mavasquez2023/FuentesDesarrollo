package cl.laaraucana.RentasPrevired.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AFIERRAR")
public class ArchivoErrorEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	@Column(name = "NOMARCH", length = 50)
	private String NOMARCH;
	@Column(name = "TIPOREG", length = 2)
	private String TIPOREG;
	@Column(name = "LINERR", length = 8)
	private String LINERR;
	@Column(name = "CODERR", length = 4)
	private int CODERR;
	@Column(name = "GLOSAERR", length = 50)
	private String GLOSAERR;
	@Column(name = "FECHERR")
	private Date FECHERR;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNOMARCH() {
		return NOMARCH;
	}

	public void setNOMARCH(String nOMARCH) {
		NOMARCH = nOMARCH;
	}

	public String getTIPOREG() {
		return TIPOREG;
	}

	public void setTIPOREG(String tIPOREG) {
		TIPOREG = tIPOREG;
	}

	public String getLINERR() {
		return LINERR;
	}

	public void setLINERR(String lINERR) {
		LINERR = lINERR;
	}

	public int getCODERR() {
		return CODERR;
	}

	public void setCODERR(int cODERR) {
		CODERR = cODERR;
	}

	public String getGLOSAERR() {
		return GLOSAERR;
	}

	public void setGLOSAERR(String gLOSAERR) {
		GLOSAERR = gLOSAERR;
	}

	public Date getFECHERR() {
		return FECHERR;
	}

	public void setFECHERR(Date fECHERR) {
		FECHERR = fECHERR;
	}

}
