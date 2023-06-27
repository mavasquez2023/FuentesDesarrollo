package cl.araucana.cp.presentation.struts.forms.cargasFamiliares;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class ConsultaCargasActionForm extends ActionForm
{
	private static final long serialVersionUID = 6687264303807600020L;

	private String rut;
	private String nombre;
	private String apellidos;
	//private Collection numeroFilas;
	private List trabajadores;
	private List empresas;
	private List cargas;

	private String rutOculto;
	private String nombreOculto;
	private String apellidosOculto;

	private String rutEmpresa;
	private String dvRutEmpresa;
	private String razonSocial;


	private String mostrarLista;

	private int estadoNomina;

	public int getEstadoNomina()
	{
		return this.estadoNomina;
	}

	public void setEstadoNomina(int estadoNomina)
	{
		this.estadoNomina = estadoNomina;
	}

	public String getApellidos()
	{
		return this.apellidos;
	}

	public void setApellidos(String apellidos)
	{
		this.apellidos = apellidos;
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getRut()
	{
		return this.rut;
	}

	public void setRut(String rut)
	{
		this.rut = rut;
	}

	public List getTrabajadores()
	{
		return this.trabajadores;
	}

	public void setTrabajadores(List trabajadores)
	{
		this.trabajadores = trabajadores;
	}

	public String getApellidosOculto()
	{
		return this.apellidosOculto;
	}

	public void setApellidosOculto(String apellidosOculto)
	{
		this.apellidosOculto = apellidosOculto;
	}

	public String getNombreOculto()
	{
		return this.nombreOculto;
	}

	public void setNombreOculto(String nombreOculto)
	{
		this.nombreOculto = nombreOculto;
	}

	public String getRutOculto()
	{
		return this.rutOculto;
	}

	public void setRutOculto(String rutOculto)
	{
		this.rutOculto = rutOculto;
	}

	public String getDvRutEmpresa()
	{
		return dvRutEmpresa;
	}

	public void setDvRutEmpresa(String dvRutEmpresa)
	{
		this.dvRutEmpresa = dvRutEmpresa;
	}

	public String getRazonSocial()
	{
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	public String getRutEmpresa()
	{
		return rutEmpresa;
	}

	public void setRutEmpresa(String rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	public String getMostrarLista()
	{
		return mostrarLista;
	}

	public void setMostrarLista(String mostrarLista)
	{
		this.mostrarLista = mostrarLista;
	}

	public List getEmpresas()
	{
		return empresas;
	}

	public void setEmpresas(List empresas)
	{
		this.empresas = empresas;
	}

	public List getCargas()
	{
		return cargas;
	}

	public void setCargas(List cargas)
	{
		this.cargas = cargas;
	}
}