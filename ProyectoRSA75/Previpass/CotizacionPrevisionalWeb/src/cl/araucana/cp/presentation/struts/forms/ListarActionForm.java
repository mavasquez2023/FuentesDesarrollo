package cl.araucana.cp.presentation.struts.forms;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/*
 * @(#) ListarActionForm.java 1.8 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * 
 * @version 1.8
 */
public class ListarActionForm extends ActionForm
{
	private static final long serialVersionUID = -6355530496104624302L;
	private List empresas, tiposNomina, estados, nominas;
	private String tipoNomina;
	private Integer empresa, estado;
	private String[] imgsrcs;
	private String[] chkBoxes;
	private boolean mostrarTotal;
	private Collection numeroFilas;
	private int hayPagables;

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
	 * emppresas
	 * 
	 * @param empresas
	 */
	public void setEmpresas(List empresas)
	{
		this.empresas = empresas;
	}

	/**
	 * estados
	 * 
	 * @return
	 */
	public List getEstados()
	{
		return this.estados;
	}

	/**
	 * estados
	 * 
	 * @param estados
	 */
	public void setEstados(List estados)
	{
		this.estados = estados;
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
	 * empresa
	 * 
	 * @return
	 */
	public Integer getEmpresa()
	{
		return this.empresa;
	}

	/**
	 * empresa
	 * 
	 * @param empresa
	 */
	public void setEmpresa(Integer empresa)
	{
		this.empresa = empresa;
	}

	/**
	 * estado
	 * 
	 * @return
	 */
	public Integer getEstado()
	{
		return this.estado;
	}

	/**
	 * estado
	 * 
	 * @param estado
	 */
	public void setEstado(Integer estado)
	{
		this.estado = estado;
	}

	/**
	 * nominas
	 * 
	 * @return
	 */
	public List getNominas()
	{
		return this.nominas;
	}

	/**
	 * nominas
	 * 
	 * @param nominas
	 */
	public void setNominas(List nominas)
	{
		this.nominas = nominas;
	}

	/**
	 * imgsrcs
	 * 
	 * @return
	 */
	public String[] getImgsrcs()
	{
		return this.imgsrcs;
	}

	/**
	 * imgsrcs
	 * 
	 * @param imgsrcs
	 */
	public void setImgsrcs(String[] imgsrcs)
	{
		this.imgsrcs = imgsrcs;
	}

	/**
	 * check boxes
	 * 
	 * @return
	 */
	public String[] getChkBoxes()
	{
		return this.chkBoxes;
	}

	/**
	 * check boxes
	 * 
	 * @param chkBoxes
	 */
	public void setChkBoxes(String[] chkBoxes)
	{
		this.chkBoxes = chkBoxes;
	}

	/**
	 * reset
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
		super.reset(arg0, arg1);

		this.tipoNomina = null;
		this.empresa = null;
		this.estado = null;
		this.mostrarTotal = false;

		this.chkBoxes = new String[0];
	}

	/**
	 * mostrar total
	 * 
	 * @return
	 */
	public boolean isMostrarTotal()
	{
		return this.mostrarTotal;
	}

	/**
	 * mostrar total
	 * 
	 * @param mostrarTotal
	 */
	public void setMostrarTotal(boolean mostrarTotal)
	{
		this.mostrarTotal = mostrarTotal;
	}

	/**
	 * numero filas
	 * 
	 * @return
	 */
	public Collection getnumeroFilas()
	{
		return this.numeroFilas;
	}

	/**
	 * numero filas
	 * 
	 * @param numeroFilas
	 */
	public void setnumeroFilas(Collection numeroFilas)
	{
		this.numeroFilas = numeroFilas;
	}

	/**
	 * hay pagables
	 * 
	 * @return
	 */
	public int getHayPagables()
	{
		return this.hayPagables;
	}

	/**
	 * hay pagables
	 * 
	 * @param hayPagables
	 */
	public void setHayPagables(int hayPagables)
	{
		this.hayPagables = hayPagables;
	}
}
