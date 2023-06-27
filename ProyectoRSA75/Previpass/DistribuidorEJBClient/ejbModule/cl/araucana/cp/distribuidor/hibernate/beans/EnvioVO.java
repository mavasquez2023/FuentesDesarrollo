package cl.araucana.cp.distribuidor.hibernate.beans;

import java.sql.Timestamp;
import java.util.HashMap;

public class EnvioVO extends AuditableVO
{
	private static final long serialVersionUID = -632437647931963724L;
	private int id;
	private int idEstado;
	private int idNodo;
	private int idEncargado;
	private Timestamp recibido;
	private int duracion;
	private int numNominas;
	private int numAceptadas;
	private String direccionIPRemitente;
	private String excepcion;

	public EnvioVO()
	{
		super();
	}

	public EnvioVO(int idEstado, int idNodo, int idEncargado, Timestamp recibido, int duracion, int numNominas, int numAceptadas)
	{
		super();
		this.idEstado = idEstado;
		this.idNodo = idNodo;
		this.idEncargado = idEncargado;
		this.recibido = recibido;
		this.duracion = duracion;
		this.numNominas = numNominas;
		this.numAceptadas = numAceptadas;
	}

	public EnvioVO(int idEstado, int idNodo, int idEncargado, Timestamp recibido, int duracion, int numNominas, int numAceptadas, String direcIP)
	{
		super();
		this.idEstado = idEstado;
		this.idNodo = idNodo;
		this.idEncargado = idEncargado;
		this.recibido = recibido;
		this.duracion = duracion;
		this.numNominas = numNominas;
		this.numAceptadas = numAceptadas;
		this.direccionIPRemitente = direcIP;
	}

	public int getDuracion()
	{
		return this.duracion;
	}
	public void setDuracion(int duracion)
	{
		this.duracion = duracion;
	}
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getIdEncargado()
	{
		return this.idEncargado;
	}
	public void setIdEncargado(int idEncargado)
	{
		this.idEncargado = idEncargado;
	}
	public int getIdEstado()
	{
		return this.idEstado;
	}
	public void setIdEstado(int idEstado)
	{
		this.idEstado = idEstado;
	}
	public int getIdNodo()
	{
		return this.idNodo;
	}
	public void setIdNodo(int idNodo)
	{
		this.idNodo = idNodo;
	}
	public int getNumAceptadas()
	{
		return this.numAceptadas;
	}
	public void setNumAceptadas(int numAceptadas)
	{
		this.numAceptadas = numAceptadas;
	}
	public int getNumNominas()
	{
		return this.numNominas;
	}
	public void setNumNominas(int numNominas)
	{
		this.numNominas = numNominas;
	}
	public void addNumNominas()
	{
		this.numNominas++;
	}

	public Timestamp getRecibido() {
		return this.recibido;
	}

	public void setRecibido(Timestamp recibido) {
		this.recibido = recibido;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		
		parametros.put("1", "" + this.id);
		parametros.put("2", "" + this.idEstado);
		parametros.put("3", "" + this.idNodo);
		parametros.put("4", "" + this.idEncargado);
		parametros.put("5", String.valueOf(this.recibido));
		parametros.put("6", "" + this.duracion);
		parametros.put("7", "" + this.numNominas);
		parametros.put("8", "" + this.numAceptadas);
		
		return parametros;		
	}

	public String getExcepcion()
	{
		return this.excepcion;
	}

	public void setExcepcion(String excepcion)
	{
		this.excepcion = excepcion;
	}

	public String getDireccionIPRemitente()
	{
		return this.direccionIPRemitente;
	}

	public void setDireccionIPRemitente(String direccionIPRemitente)
	{
		this.direccionIPRemitente = direccionIPRemitente;
	}
}
