package cl.araucana.wslme.business.to;

import java.util.Date;

public class Estadistica {

	private Date fecha;
	private Integer solTot;
	private Integer respOkConPago;
	private Integer respOkSinPago;
	private Integer respNok;
	private Integer respTot;
	private String horaPrimeraSol;
	private String horaUltimaSol;
	
	public Estadistica(){}

	public Estadistica(Date fecha, Integer solTot, Integer respOkConPago, Integer respOkSinPago, Integer respNok, Integer respTot, String horaPrimeraSol, String horaUltimaSol) {
		super();
		this.fecha = fecha;
		this.solTot = solTot;
		this.respOkConPago = respOkConPago;
		this.respOkSinPago = respOkSinPago;
		this.respNok = respNok;
		this.respTot = respTot;
		this.horaPrimeraSol = horaPrimeraSol;
		this.horaUltimaSol = horaUltimaSol;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHoraPrimeraSol() {
		return horaPrimeraSol;
	}

	public void setHoraPrimeraSol(String horaPrimeraSol) {
		this.horaPrimeraSol = horaPrimeraSol;
	}

	public String getHoraUltimaSol() {
		return horaUltimaSol;
	}

	public void setHoraUltimaSol(String horaUltimaSol) {
		this.horaUltimaSol = horaUltimaSol;
	}

	public Integer getRespNok() {
		return respNok;
	}

	public void setRespNok(Integer respNok) {
		this.respNok = respNok;
	}

	public Integer getRespOkConPago() {
		return respOkConPago;
	}

	public void setRespOkConPago(Integer respOkConPago) {
		this.respOkConPago = respOkConPago;
	}

	public Integer getRespOkSinPago() {
		return respOkSinPago;
	}

	public void setRespOkSinPago(Integer respOkSinPago) {
		this.respOkSinPago = respOkSinPago;
	}

	public Integer getSolTot() {
		return solTot;
	}

	public void setSolTot(Integer solTot) {
		this.solTot = solTot;
	}

	public Integer getRespTot() {
		return respTot;
	}

	public void setRespTot(Integer respTot) {
		this.respTot = respTot;
	}
	
}
