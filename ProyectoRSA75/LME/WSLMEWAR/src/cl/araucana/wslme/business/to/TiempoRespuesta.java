package cl.araucana.wslme.business.to;

import java.util.Date;

public class TiempoRespuesta {

	private Date fecha;
	private Integer segundos;
	private Integer cantSol;
	
	public TiempoRespuesta(){}

	public TiempoRespuesta(Date fecha, Integer segundos, Integer cantSol) {
		super();
		this.fecha = fecha;
		this.segundos = segundos;
		this.cantSol = cantSol;
	}

	public Integer getCantSol() {
		return cantSol;
	}

	public void setCantSol(Integer cantSol) {
		this.cantSol = cantSol;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getSegundos() {
		return segundos;
	}

	public void setSegundos(Integer segundos) {
		this.segundos = segundos;
	}

		
}
