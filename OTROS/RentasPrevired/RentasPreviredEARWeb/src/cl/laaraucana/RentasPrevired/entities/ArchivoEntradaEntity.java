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
// was @Table(name = "ARCHFTP", schema = "ALEXM")
@Table(name = "ARCHFTP")
public class ArchivoEntradaEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long ID;
	@Column(name = "NOMBRE")
	private String NOMBRE;
	@Column(name = "STATUS")
	private int STATUS;
	@Column(name = "FECHACARGA")
	private Date FECHACARGA;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}

	public Date getFECHACARGA() {
		return FECHACARGA;
	}

	public void setFECHACARGA(Date fECHACARGA) {
		FECHACARGA = fECHACARGA;
	}

}
