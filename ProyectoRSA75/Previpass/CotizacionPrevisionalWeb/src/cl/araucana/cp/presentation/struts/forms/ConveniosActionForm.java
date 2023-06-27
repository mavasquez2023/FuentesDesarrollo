package cl.araucana.cp.presentation.struts.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cp.presentation.struts.javaBeans.LineaComprobanteProvisorioVO;

/*
 * @(#) ConveniosActionForm.java 1.15 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.15
 */
public class ConveniosActionForm extends ActionForm
{

	private static final long serialVersionUID = -8224834474758516298L;

	private String operacion;

	private String rutFmt;
	private List consulta, tiposNomina, empresas;
	private List objEmpresas;
	private String nombreEmpresa, tipoNomina;
	private int rut, convenio;
	private long totalP, totalDNP, totalNP;
	private long codigoBarra;

	private boolean puedeGuardarOPagar;

	/**
	 * reset
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{

		this.operacion = "";

		this.rutFmt = "";
		this.consulta = ListUtils.lazyList(new ArrayList(), new Factory()
		{
			public Object create()
			{
				return new LineaComprobanteProvisorioVO("");
			}
		});
		this.tiposNomina = null;
		this.empresas = null;
		this.objEmpresas = null;
		this.nombreEmpresa = "";
		this.tipoNomina = null;
		this.convenio = -1;
		this.rut = -1;
		this.totalP = 0;
		this.totalDNP = 0;
		this.totalNP = 0;
	}

	/**
	 * consulta
	 * 
	 * @return
	 */
	public List getConsulta()
	{
		return this.consulta;
	}

	/**
	 * consulta
	 * 
	 * @param consulta
	 */
	public void setConsulta(List consulta)
	{
		this.consulta = consulta;
	}

	/**
	 * nombre empresa
	 * 
	 * @return
	 */
	public String getNombreEmpresa()
	{
		return this.nombreEmpresa;
	}

	/**
	 * nombre empresa
	 * 
	 * @param nombreEmpresa
	 */
	public void setNombreEmpresa(String nombreEmpresa)
	{
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * rut
	 * 
	 * @return
	 */
	public int getRut()
	{
		return this.rut;
	}

	/**
	 * rut
	 * 
	 * @param rut
	 */
	public void setRut(int rut)
	{
		this.rut = rut;
	}

	/**
	 * rut fmt
	 * 
	 * @return
	 */
	public String getRutFmt()
	{
		return this.rutFmt;
	}

	/**
	 * rut fmt
	 * 
	 * @param rutFmt
	 */
	public void setRutFmt(String rutFmt)
	{
		this.rutFmt = rutFmt;
	}

	/**
	 * tipo nomina
	 * 
	 * @return
	 */
	public String getTipoNomina()
	{
		return this.tipoNomina;
	}

	/**
	 * tipo nomina
	 * 
	 * @param tipoNomina
	 */
	public void setTipoNomina(String tipoNomina)
	{
		this.tipoNomina = tipoNomina;
	}

	/**
	 * tipos nomina
	 * 
	 * @return
	 */
	public List getTiposNomina()
	{
		return this.tiposNomina;
	}

	/**
	 * tipos nomina
	 * 
	 * @param tiposNomina
	 */
	public void setTiposNomina(List tiposNomina)
	{
		this.tiposNomina = tiposNomina;
	}

	/**
	 * total dnp
	 * 
	 * @return
	 */
	public long getTotalDNP()
	{
		return this.totalDNP;
	}

	/**
	 * total dnp
	 * 
	 * @param totalDNP
	 */
	public void setTotalDNP(long totalDNP)
	{
		this.totalDNP = totalDNP;
	}

	/**
	 * total np
	 * 
	 * @return
	 */
	public long getTotalNP()
	{
		return this.totalNP;
	}

	/**
	 * total np
	 * 
	 * @param totalNP
	 */
	public void setTotalNP(long totalNP)
	{
		this.totalNP = totalNP;
	}

	/**
	 * total p
	 * 
	 * @return
	 */
	public long getTotalP()
	{
		return this.totalP;
	}

	/**
	 * total p
	 * 
	 * @param totalP
	 */
	public void setTotalP(long totalP)
	{
		this.totalP = totalP;
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
	 * operacion
	 * 
	 * @return
	 */
	public String getOperacion()
	{
		return this.operacion;
	}

	/**
	 * operacion
	 * 
	 * @param operacion
	 */
	public void setOperacion(String operacion)
	{
		this.operacion = operacion;
	}

	/**
	 * codigo barra
	 * 
	 * @return
	 */
	public String getCodigoBarra()
	{
		return Long.toString(this.codigoBarra);
	}

	/**
	 * codigo barra
	 * 
	 * @param ton
	 * @return
	 */
	public long getCodigoBarra(long ton)
	{
		return this.codigoBarra;
	}

	/**
	 * codigo barra
	 * 
	 * @param codigoBarra
	 */
	public void setCodigoBarra(String codigoBarra)
	{
		this.codigoBarra = Long.parseLong(codigoBarra);
	}

	/**
	 * empresas
	 * 
	 * @return
	 */
	public List getEmpresas()
	{
		return this.empresas;
	}

	/**
	 * empresas
	 * 
	 * @param empresas
	 */
	public void setEmpresas(List empresas)
	{
		this.empresas = empresas;
	}

	/**
	 * puede guardar
	 * 
	 * @return
	 */
	public boolean getPuedeGuardarOPagar()
	{
		return this.puedeGuardarOPagar;
	}

	/**
	 * puede guardar o pagar
	 * 
	 * @param puedeGuardarOPagar
	 */
	public void setPuedeGuardarOPagar(boolean puedeGuardarOPagar)
	{
		this.puedeGuardarOPagar = puedeGuardarOPagar;
	}

	/**
	 * object empresa
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
}
