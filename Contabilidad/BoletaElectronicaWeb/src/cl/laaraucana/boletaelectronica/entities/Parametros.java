package cl.laaraucana.boletaelectronica.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOLEPARAM", schema = "DTDTA")
public class Parametros implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDPAR")
	private long idpar;
	@Column(name = "CODIGO")
	private int CODIGO;
	@Column(name = "CAMPO")
	private String CAMPO;
	@Column(name = "VALOR")
	private String VALOR;
	@Column(name = "ESTADO")
	private int ESTADO;
	@Column(name = "FECCRE")
	private String FECCRE;
	@Column(name = "HORACRE")
	private String HORACRE;
	@Column(name = "FECMOD")
	private String FECMOD;
	@Column(name = "HORAMOD")
	private String HORAMOD;

	public long getIdpar() {
		return idpar;
	}

	public void setIdpar(long idpar) {
		this.idpar = idpar;
	}

	public int getCODIGO() {
		return CODIGO;
	}

	public void setCODIGO(int cODIGO) {
		CODIGO = cODIGO;
	}

	public String getCAMPO() {
		return CAMPO;
	}

	public void setCAMPO(String cAMPO) {
		CAMPO = cAMPO;
	}

	public String getVALOR() {
		return VALOR;
	}

	public void setVALOR(String vALOR) {
		VALOR = vALOR;
	}

	public int getESTADO() {
		return ESTADO;
	}

	public void setESTADO(int eSTADO) {
		ESTADO = eSTADO;
	}

	public String getFECCRE() {
		return FECCRE;
	}

	public void setFECCRE(String fECCRE) {
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

}
