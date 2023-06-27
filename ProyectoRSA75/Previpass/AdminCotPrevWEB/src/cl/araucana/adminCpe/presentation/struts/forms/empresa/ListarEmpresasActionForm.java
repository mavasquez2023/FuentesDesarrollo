package cl.araucana.adminCpe.presentation.struts.forms.empresa;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/*
* @(#) ListarEmpresaActionForm.java 1.10 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author aacuña
 * 
 * @version 1.10
 */
public class ListarEmpresasActionForm extends ActionForm
{
	private static final long serialVersionUID = -5585015647754230297L;

	private String rutEmpresaBuscar;
	private String razonSocialEmpresaBuscar;
	private List consulta;
	
	private String codGrupoConvenioBuscar;
	
	private Collection numeroFilas;
	
	private String nombreGrupoConvBuscar;
	private String codGrupoConvenioEmpresaBuscar;

	private String rutOculto;
	private String nombreOculto;
	private String razonOculto;
	private String idGrupoOculto;
	private String mostrarLista;
	
/**
 * Mostrar lista
 * @return
 */	
public String getMostrarLista() {
		return this.mostrarLista;
	}

/**
 * Mostrar lista
 * @param mostrarLista
 */
	public void setMostrarLista(String mostrarLista) {
		this.mostrarLista = mostrarLista;
	}
/**
 * Grupo Convenio Empresa Buscar
 * @return
 */
public String getGrupoConvenioEmpresaBuscar() {
		return this.codGrupoConvenioEmpresaBuscar;
	}

/**
 * grupo convenio empresa buscar
 * @param grupoConvenioEmpresaBuscar
 */
	public void setGrupoConvenioEmpresaBuscar(String grupoConvenioEmpresaBuscar) {
		this.codGrupoConvenioEmpresaBuscar = grupoConvenioEmpresaBuscar;
	}

	/**
	 * Nombre grupo convenio buscar
	 * @return
	 */
	public String getNombreGrupoConvBuscar() {
		return this.nombreGrupoConvBuscar;
	}

	/**
	 * Nombre grupo convenio buscar
	 * @param nombreGrupoConvBuscar
	 */
	public void setNombreGrupoConvBuscar(String nombreGrupoConvBuscar) {
		this.nombreGrupoConvBuscar = nombreGrupoConvBuscar;
	}

	/**
	 * reset
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		
		this.consulta = null;
	}

	/**
	 * listar consulta
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
	 * razon social empresa buscar
	 * @return
	 */
	public String getRazonSocialEmpresaBuscar()
	{
		return this.razonSocialEmpresaBuscar;
	}

	/**
	 * razon social empresa buscar
	 * @param razonSocialEmpresaBuscar
	 */
	public void setRazonSocialEmpresaBuscar(String razonSocialEmpresaBuscar)
	{
		this.razonSocialEmpresaBuscar = razonSocialEmpresaBuscar;
	}

	/**
	 * rut empresa buscar
	 * @return
	 */
	public String getRutEmpresaBuscar()
	{
		return this.rutEmpresaBuscar;
	}

	/**
	 * rut empresa buscar
	 * @param rutEmpresaBuscar
	 */
	public void setRutEmpresaBuscar(String rutEmpresaBuscar)
	{
		this.rutEmpresaBuscar = rutEmpresaBuscar;
	}

	/**
	 * numero filas
	 * @return
	 */
	public Collection getNumeroFilas() {
		return this.numeroFilas;
	}

	/**
	 * numero filas
	 * @param numeroFilas
	 */
	public void setNumeroFilas(Collection numeroFilas) {
		this.numeroFilas = numeroFilas;
	}

	/**
	 * codigo grupo convenio buscar
	 * @param codGrupoConvenioBuscar
	 */
	public void setCodGrupoConvenioBuscar(String codGrupoConvenioBuscar) {
		this.codGrupoConvenioBuscar = codGrupoConvenioBuscar;
	}

	/**
	 * codigo grupo convenio buscar
	 * @return
	 */
	public String getCodGrupoConvenioBuscar() {
		return this.codGrupoConvenioBuscar;
	}

	/**
	 * codigo grupo convenio empresa buscar
	 * @return
	 */
	public String getCodGrupoConvenioEmpresaBuscar() {
		return this.codGrupoConvenioEmpresaBuscar;
	}

	/**
	 * codigo grupo convenio empresa buscar
	 * @param codGrupoConvenioEmpresaBuscar
	 */
	public void setCodGrupoConvenioEmpresaBuscar(
			String codGrupoConvenioEmpresaBuscar) {
		this.codGrupoConvenioEmpresaBuscar = codGrupoConvenioEmpresaBuscar;
	}

	/**
	 * id grupo oculto
	 * @return
	 */
	public String getIdGrupoOculto() {
		return this.idGrupoOculto;
	}

	/**
	 * id grupo oculto
	 * @param idGrupoOculto
	 */
	public void setIdGrupoOculto(String idGrupoOculto) {
		this.idGrupoOculto = idGrupoOculto;
	}

	/**
	 * nombre oculto
	 * @return
	 */
	public String getNombreOculto() {
		return this.nombreOculto;
	}

	/**
	 * nombre oculto
	 * @param nombreOculto
	 */
	public void setNombreOculto(String nombreOculto) {
		this.nombreOculto = nombreOculto;
	}

	/** 
	 * razon oculto
	 * @return
	 */
	public String getRazonOculto() {
		return this.razonOculto;
	}

	/**
	 * razon oculto
	 * @param razonOculto
	 */
	public void setRazonOculto(String razonOculto) {
		this.razonOculto = razonOculto;
	}

	/**
	 * rut oculto
	 * @return
	 */
	public String getRutOculto() {
		return this.rutOculto;
	}

	/**
	 * rut oculto
	 * @param rutOculto
	 */
	public void setRutOculto(String rutOculto) {
		this.rutOculto = rutOculto;
	}
	
	
}
