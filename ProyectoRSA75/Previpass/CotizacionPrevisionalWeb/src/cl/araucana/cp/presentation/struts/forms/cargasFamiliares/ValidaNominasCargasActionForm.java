package cl.araucana.cp.presentation.struts.forms.cargasFamiliares;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class ValidaNominasCargasActionForm extends ActionForm
{
	private static final long serialVersionUID = 7959958632298446530L;

	private String[] codigos;
	private String rutFmt, rut, nombreEmpresa, convenio, tipoNomina, operacion;
	private List tiposNomina, consulta, empresas;
	
	//Evaluar si eliminar
	private String[] imgsrcs;
	private Integer empresa;
	
	public String[] getCodigos()
	{
		return codigos;
	}
	public void setCodigos(String[] codigos)
	{
		this.codigos = codigos;
	}
	public List getConsulta()
	{
		return consulta;
	}
	public void setConsulta(List consulta)
	{
		this.consulta = consulta;
	}
	public String getConvenio()
	{
		return convenio;
	}
	public void setConvenio(String convenio)
	{
		this.convenio = convenio;
	}
	public List getEmpresas()
	{
		return empresas;
	}
	public void setEmpresas(List empresas)
	{
		this.empresas = empresas;
	}
	public String getNombreEmpresa()
	{
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa)
	{
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getOperacion()
	{
		return operacion;
	}
	public void setOperacion(String operacion)
	{
		this.operacion = operacion;
	}
	public String getRut()
	{
		return rut;
	}
	public void setRut(String rut)
	{
		this.rut = rut;
	}
	public String getRutFmt()
	{
		return rutFmt;
	}
	public void setRutFmt(String rutFmt)
	{
		this.rutFmt = rutFmt;
	}
	public String getTipoNomina()
	{
		return tipoNomina;
	}
	public void setTipoNomina(String tipoNomina)
	{
		this.tipoNomina = tipoNomina;
	}
	public List getTiposNomina()
	{
		return tiposNomina;
	}
	public void setTiposNomina(List tiposNomina)
	{
		this.tiposNomina = tiposNomina;
	}
	public String[] getImgsrcs()
	{
		return imgsrcs;
	}
	public void setImgsrcs(String[] imgsrcs)
	{
		this.imgsrcs = imgsrcs;
	}
	public Integer getEmpresa()
	{
		return empresa;
	}
	public void setEmpresa(Integer empresa)
	{
		this.empresa = empresa;
	} 


}