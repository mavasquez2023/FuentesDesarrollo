package cl.laaraucana.RentasPrevired.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
// was @Table(name = "PRMAFI", schema = "ALEXM")
@Table(name = "PRMAFI")
public class AfiliadoConsultaEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	@Transient
	private CommonsMultipartFile fichero;
	@Column(name = "TIPOREG", length = 2)
	private String TIPOREGISTRO;
	@Column(name = "RUTAFI", length = 9)
	private int RUTAFILIADO;
	@Column(name = "DVAFI", length = 1)
	private String DVAFILIADO;
	@Column(name = "NPERIODOS", length = 2)
	private int NPERIODOS;
	@Column(name = "FECEMILIC", length = 10)
	private String FECHAEMISIONLICENCIA;
	@Column(name = "FECINILIC", length = 10)
	private String FECHAINICIOLICENCIA;
	@Column(name = "FOLLICE", length = 12)
	private long FOLIOLICENCIA;
	@Column(name = "TIPOLIC", length = 1)
	private int TIPOLICENCIA;
	@Column(name = "TIPOCONS", length = 1)
	private int TIPOCONSULTA;
	@Column(name = "USUCREA", length = 20)
	private String USUARIOCREADOR;
	@Column(name = "FECCREA")
	private Date FECHACREACION;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public CommonsMultipartFile getFichero() {
		return fichero;
	}

	public void setFichero(CommonsMultipartFile fichero) {
		this.fichero = fichero;
	}

	public String getTIPOREGISTRO() {
		return TIPOREGISTRO;
	}

	public void setTIPOREGISTRO(String tIPOREGISTRO) {
		TIPOREGISTRO = tIPOREGISTRO;
	}

	public int getRUTAFILIADO() {
		return RUTAFILIADO;
	}

	public void setRUTAFILIADO(int rUTAFILIADO) {
		RUTAFILIADO = rUTAFILIADO;
	}

	public String getDVAFILIADO() {
		return DVAFILIADO;
	}

	public void setDVAFILIADO(String dVAFILIADO) {
		DVAFILIADO = dVAFILIADO;
	}

	public int getNPERIODOS() {
		return NPERIODOS;
	}

	public void setNPERIODOS(int nPERIODOS) {
		NPERIODOS = nPERIODOS;
	}

	public String getFECHAEMISIONLICENCIA() {
		return FECHAEMISIONLICENCIA;
	}

	public void setFECHAEMISIONLICENCIA(String fECHAEMISIONLICENCIA) {
		FECHAEMISIONLICENCIA = fECHAEMISIONLICENCIA;
	}

	public String getFECHAINICIOLICENCIA() {
		return FECHAINICIOLICENCIA;
	}

	public void setFECHAINICIOLICENCIA(String fECHAINICIOLICENCIA) {
		FECHAINICIOLICENCIA = fECHAINICIOLICENCIA;
	}

	public long getFOLIOLICENCIA() {
		return FOLIOLICENCIA;
	}

	public void setFOLIOLICENCIA(long fOLIOLICENCIA) {
		FOLIOLICENCIA = fOLIOLICENCIA;
	}

	public int getTIPOLICENCIA() {
		return TIPOLICENCIA;
	}

	public void setTIPOLICENCIA(int tIPOLICENCIA) {
		TIPOLICENCIA = tIPOLICENCIA;
	}

	public int getTIPOCONSULTA() {
		return TIPOCONSULTA;
	}

	public void setTIPOCONSULTA(int tIPOCONSULTA) {
		TIPOCONSULTA = tIPOCONSULTA;
	}

	public String getUSUARIOCREADOR() {
		return USUARIOCREADOR;
	}

	public void setUSUARIOCREADOR(String uSUARIOCREADOR) {
		USUARIOCREADOR = uSUARIOCREADOR;
	}

	public Date getFECHACREACION() {
		return FECHACREACION;
	}

	public void setFECHACREACION(Date fECHACREACION) {
		FECHACREACION = fECHACREACION;
	}

}
