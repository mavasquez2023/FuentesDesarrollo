package cl.araucana.cp.distribuidor.hibernate.beans;

import java.sql.Date;
import java.sql.Timestamp;

public class CalendarioVO extends AuditableVO
{
	private static final long serialVersionUID = 1L;
	
	private int idCal;
	private String mes;
	private Date fecha1;
	private Date fecha2;
	private Date fecha3;
	private Timestamp fecha4;
	
	public Date getFecha1() {
		return this.fecha1;
	}
	public void setFecha1(Date fecha1) {
		this.fecha1 = fecha1;
	}
	public Date getFecha2() {
		return this.fecha2;
	}
	public void setFecha2(Date fecha2) {
		this.fecha2 = fecha2;
	}
	public Date getFecha3() {
		return this.fecha3;
	}
	public void setFecha3(Date fecha3) {
		this.fecha3 = fecha3;
	}
	public Timestamp getFecha4() {
		return this.fecha4;
	}
	public void setFecha4(Timestamp fecha4) {
		this.fecha4 = fecha4;
	}
	public String getMes() {
		return this.mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public int getIdCal() {
		return this.idCal;
	}
	public void setIdCal(int idCal) {
		this.idCal = idCal;
	}
	
		
}
