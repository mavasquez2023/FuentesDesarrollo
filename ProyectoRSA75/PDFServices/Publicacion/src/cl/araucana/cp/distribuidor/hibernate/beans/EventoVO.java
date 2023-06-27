package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;
import java.sql.Date;

public class EventoVO implements Serializable
{	
	private static final long serialVersionUID = -5539286601115753558L;
	private int idEvento;
	private int idTipoEvento;
	private int idAgente;
	private Date cuando;
	private String parametros;
	
	public EventoVO()
	{
		super();
	}
	
	
	public int getIdAgente() {
		return this.idAgente;
	}
	public void setIdAgente(int idAgente) {
		this.idAgente = idAgente;
	}
	public int getIdEvento() {
		return this.idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public int getIdTipoEvento() {
		return this.idTipoEvento;
	}
	public void setIdTipoEvento(int idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}
	public String getParametros() {
		return this.parametros;
	}
	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	public String toString() {
		
		return "EventoVO[idEvento: " + this.getIdEvento()+", idTipoEvento: " + this.getIdTipoEvento() +", idAgente: " + this.getIdAgente() + ", Cuando: " + this.getCuando()+ ", Parametros: " + this.getParametros() +  "\"]";
	}


	public Date getCuando() {
		return this.cuando;
	}


	public void setCuando(Date cuando) {
		this.cuando = cuando;
	}

}
