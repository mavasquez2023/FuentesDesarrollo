package cl.araucana.clientewsfonasa.business.to;

import java.util.Date;

public class LogWSFonasaTO {
	
	private Integer id;
	private Integer idCall;
	private Date fechaHora;
	private Short codLog;
	private String descLog;
	
	public LogWSFonasaTO(){}

	public LogWSFonasaTO(Integer id, Integer idCall, Date fechaHora, Short codLog, String descLog) {
		super();
		this.id = id;
		this.idCall = idCall;
		this.fechaHora = fechaHora;
		this.codLog = codLog;
		this.descLog = descLog;
	}

	public Short getCodLog() {
		return codLog;
	}

	public void setCodLog(Short codLog) {
		this.codLog = codLog;
	}

	public String getDescLog() {
		return descLog;
	}

	public void setDescLog(String descLog) {
		this.descLog = descLog;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCall() {
		return idCall;
	}

	public void setIdCall(Integer idCall) {
		this.idCall = idCall;
	}
	
	
}
