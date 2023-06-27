package cl.araucana.cp.presentation.struts.forms.comprobante;

import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;

/*
 * @(#) DetalleActionForm.java 1.4 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.4
 */
public class DetalleActionForm extends ActionForm
{
	private static final long serialVersionUID = -9175921803427252932L;

	private String idCodigoBarras;
	private char tipoProceso;
	private int convenio;
	private int rutEmpresa;
	private int puedePagar;
	private String rutEmpresaFormat;
	private String razonSocial;
	private String totalCmp;
	private long totalLong;

	private Collection empresas;
	private Collection tiposProcesos;
	private List objEmpresas;

	private List secciones;

	private String detallesMostrar;
	private String estadoImpresion;
	private String periodo;

	/**
	 * tipos procesos
	 * 
	 * @return
	 */
	public Collection getTiposProcesos()
	{
		return this.tiposProcesos;
	}

	/**
	 * tipos procesos
	 * 
	 * @param tiposProcesos
	 */
	public void setTiposProcesos(Collection tiposProcesos)
	{
		this.tiposProcesos = tiposProcesos;
	}

	/**
	 * id codigo barras
	 * 
	 * @return
	 */
	public String getIdCodigoBarras()
	{
		return this.idCodigoBarras;
	}

	/**
	 * id codigo barras
	 * 
	 * @param idCodigoBarras
	 */
	public void setIdCodigoBarras(String idCodigoBarras)
	{
		this.idCodigoBarras = idCodigoBarras;
	}

	/**
	 * convenio
	 * 
	 * @return
	 */
	public int getConvenio()
	{
		return this.convenio;
	}

	/**
	 * convenio
	 * 
	 * @param convenio
	 */
	public void setConvenio(int convenio)
	{
		this.convenio = convenio;
	}

	/**
	 * tipo proceso
	 * 
	 * @return
	 */
	public char getTipoProceso()
	{
		return this.tipoProceso;
	}

	/**
	 * tipo prodeso
	 * 
	 * @param tipoProceso
	 */
	public void setTipoProceso(char tipoProceso)
	{
		this.tipoProceso = tipoProceso;
	}

	/**
	 * razon sosial
	 * 
	 * @return
	 */
	public String getRazonSocial()
	{
		return this.razonSocial;
	}

	/**
	 * razon social
	 * 
	 * @param razonSocial
	 */
	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * rut empresa
	 * 
	 * @return
	 */
	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}

	/**
	 * rut emprerasa
	 * 
	 * @param rutEmpresa
	 */
	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	/**
	 * rut empresa formut
	 * 
	 * @return
	 */
	public String getRutEmpresaFormat()
	{
		return this.rutEmpresaFormat;
	}

	/**
	 * rut empresa format
	 * 
	 * @param rutEmpresaFormat
	 */
	public void setRutEmpresaFormat(String rutEmpresaFormat)
	{
		this.rutEmpresaFormat = rutEmpresaFormat;
	}

	/**
	 * secciones
	 * 
	 * @return
	 */
	public List getSecciones()
	{
		return this.secciones;
	}

	/**
	 * secciones
	 * 
	 * @param secciones
	 */
	public void setSecciones(List secciones)
	{
		this.secciones = secciones;
	}

	/**
	 * total cmp
	 * 
	 * @return
	 */
	public String getTotalCmp()
	{
		return this.totalCmp;
	}

	/**
	 * total cmp
	 * 
	 * @param totalCmp
	 */
	public void setTotalCmp(String totalCmp)
	{
		this.totalCmp = totalCmp;
	}

	/**
	 * empresas
	 * 
	 * @return
	 */
	public Collection getEmpresas()
	{
		return this.empresas;
	}

	/**
	 * empresas
	 * 
	 * @param empresas
	 */
	public void setEmpresas(Collection empresas)
	{
		this.empresas = empresas;
	}

	/**
	 * puede pagar
	 * 
	 * @return
	 */
	public int getPuedePagar()
	{
		return this.puedePagar;
	}

	/**
	 * puede pagar
	 * 
	 * @param puedePagar
	 */
	public void setPuedePagar(int puedePagar)
	{
		this.puedePagar = puedePagar;
	}

	/**
	 * object empresas
	 * 
	 * @return
	 */
	public List getObjEmpresas()
	{
		return this.objEmpresas;
	}

	/**
	 * object empresas
	 * 
	 * @param objEmpresas
	 */
	public void setObjEmpresas(List objEmpresas)
	{
		this.objEmpresas = objEmpresas;
	}

	public String getDetallesMostrar()
	{
		return this.detallesMostrar;
	}

	public void setDetallesMostrar(String detallesMostrar)
	{
		this.detallesMostrar = detallesMostrar;
	}

	public String getEstadoImpresion()
	{
		return this.estadoImpresion;
	}

	public void setEstadoImpresion(String estadoImpresion)
	{
		this.estadoImpresion = estadoImpresion;
	}

	public String getPeriodo()
	{
		return this.periodo;
	}

	public void setPeriodo(String periodo)
	{
		this.periodo = periodo;
	}

	public long getTotalLong()
	{
		return this.totalLong;
	}

	public void setTotalLong(long totalLong)
	{
		this.totalLong = totalLong;
	}
}
