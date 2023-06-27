package cl.araucana.adminCpe.presentation.struts.forms.mapeo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cp.distribuidor.presentation.beans.LineaMapeoConcepto;
/*
* @(#) MapeoArchivosActionForm.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * 
 * @version 1.1
 */
public class MapeoArchivosActionForm extends ActionForm
{
	private static final long serialVersionUID = 1545671511130334435L;

	private String opcGrupoConvenio;
	private String opcTipoNomina;
	private String descripcionR;
	private String descripcionG;
	private String descripcionA;
	private String descripcionD;
	private List gruposConvenio;
	private List tiposNomina;
	private List consulta;
	/**
	 * reset
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.opcGrupoConvenio = null;
		this.opcTipoNomina = null;
		this.gruposConvenio = null;
		this.tiposNomina = null;
		this.consulta = ListUtils.lazyList(new ArrayList(),
				new Factory() 
				{
					public Object create() 
					{
						return new LineaMapeoConcepto();
					}
				});
	}
	/**
	 * opc tipo nomina
	 * @return
	 */
	public String getOpcTipoNomina()
	{
		return this.opcTipoNomina;
	}
	/**
	 * opc tipo nomina
	 * @param opcTipoNomina
	 */
	public void setOpcTipoNomina(String opcTipoNomina)
	{
		this.opcTipoNomina = opcTipoNomina;
	}
	/**
	 * tipos nomina
	 * @return
	 */
	public List getTiposNomina()
	{
		return this.tiposNomina;
	}
	/**
	 * tipos nomina
	 * @param tiposNomina
	 */
	public void setTiposNomina(List tiposNomina)
	{
		this.tiposNomina = tiposNomina;
	}
	/**
	 * grupos convenio
	 * @return
	 */
	public List getGruposConvenio()
	{
		return this.gruposConvenio;
	}
	/**
	 * grupos convenio
	 * @param gruposConvenio
	 */
	public void setGruposConvenio(List gruposConvenio)
	{
		this.gruposConvenio = gruposConvenio;
	}
	/**
	 * opc grupo convenio
	 * @return
	 */
	public String getOpcGrupoConvenio()
	{
		return this.opcGrupoConvenio;
	}
	/**
	 * opc grupo convenio
	 * @param opcGrupoConvenio
	 */
	public void setOpcGrupoConvenio(String opcGrupoConvenio)
	{
		this.opcGrupoConvenio = opcGrupoConvenio;
	}
	/**
	 * consulta
	 * @return
	 */
	public List getConsulta()
	{
		return this.consulta;
	}
	/**
	 * consulta
	 * @param consulta
	 */
	public void setConsulta(List consulta)
	{
		this.consulta = consulta;
	}

	/**
	 * descripcionA
	 * @return
	 */
	public String getDescripcionA()
	{
		return this.descripcionA;
	}

	/**
	 * descripcionA
	 * @param descripcionA
	 */
	public void setDescripcionA(String descripcionA)
	{
		this.descripcionA = descripcionA;
	}

	/**
	 * descripcionD
	 * @return
	 */
	public String getDescripcionD()
	{
		return this.descripcionD;
	}

	/**
	 * descripcionD
	 * @param descripcionD
	 */
	public void setDescripcionD(String descripcionD)
	{
		this.descripcionD = descripcionD;
	}

	/**
	 * descripcionG
	 * @return
	 */
	public String getDescripcionG()
	{
		return this.descripcionG;
	}

	/**
	 * descripcionG
	 * @param descripcionG
	 */
	public void setDescripcionG(String descripcionG)
	{
		this.descripcionG = descripcionG;
	}

	/**
	 * descripcionR
	 * @return
	 */
	public String getDescripcionR()
	{
		return this.descripcionR;
	}

	/**
	 * descripcionR
	 * @param descripcionR
	 */
	public void setDescripcionR(String descripcionR)
	{
		this.descripcionR = descripcionR;
	}
	
}
