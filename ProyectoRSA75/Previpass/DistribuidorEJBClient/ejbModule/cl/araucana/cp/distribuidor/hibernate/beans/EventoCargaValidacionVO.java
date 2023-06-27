package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


public class EventoCargaValidacionVO implements Serializable 
{
	private static final long serialVersionUID = 4548024932185313541L;
	
	private int id;
	private Date fecha;
	private Timestamp hora;
	private String accion;
	private String comentarios;
	private int rutCaja;
	private String usuario;

	public EventoCargaValidacionVO()
	{
		super();
	}

	public String getAccion()
	{
		return this.accion;
	}

	public void setAccion(String accion)
	{
		this.accion = accion;
	}

	public String getComentarios()
	{
		return this.comentarios;
	}

	public void setComentarios(String comentarios)
	{
		this.comentarios = comentarios;
	}

	public Date getFecha()
	{
		return this.fecha;
	}

	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getRutCaja()
	{
		return this.rutCaja;
	}

	public void setRutCaja(int rutCaja)
	{
		this.rutCaja = rutCaja;
	}

	public String getUsuario()
	{
		return this.usuario;
	}

	public void setUsuario(String usuario)
	{
		this.usuario = usuario;
	}

	public Timestamp getHora()
	{
		return this.hora;
	}

	public void setHora(Timestamp hora)
	{
		this.hora = hora;
	}
}