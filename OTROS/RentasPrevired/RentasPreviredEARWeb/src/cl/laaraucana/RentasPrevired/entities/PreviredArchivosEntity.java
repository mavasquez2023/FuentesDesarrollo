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
@Table(name = "PRMCAAR")
public class PreviredArchivosEntity implements Serializable{

	 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	@Column(name = "NOMBRE")
	private String NOMBRE;
	@Column(name = "FECHACREACION")
	private Date FECHACREACION;
	@Column(name = "STATUS")
	private int STATUS;
	
	
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
	public Date getFECHACREACION() {
		return FECHACREACION;
	}
	public void setFECHACREACION(Date fECHACREACION) {
		FECHACREACION = fECHACREACION;
	}
	public int getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}	
	
	
	
	
}
